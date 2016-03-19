package pl.januszemotoryzacji.carfilter.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.januszemotoryzacji.carfilter.dao.model.MakeModelView;
import pl.januszemotoryzacji.carfilter.dao.model.MakeModelViewBuilder;
import pl.januszemotoryzacji.carfilter.resource.dto.MileageByProductionYearDTO;
import pl.januszemotoryzacji.carfilter.resource.dto.MileageByProductionYearDTOBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StatsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MakeModelView> findAllMakeModels() {

        return jdbcTemplate.query("select DISTINCT make as make, " +
                "model as model, " +
                "model2 as model2, " +
                "count(*) as count from offers " +
                " group by make, model, model2" +
                " order by make, model, model", (resultSet, i) -> {
            return MakeModelViewBuilder.aMakeModelView()
                    .withMake(resultSet.getString("make"))
                    .withModel(resultSet.getString("model"))
                    .withModel2(resultSet.getString("model2"))
                    .withCount(resultSet.getInt("count"))
                    .build();
        });
    }

    public List<MileageByProductionYearDTO> findMileageByProductionYear(String makeFilter, String modelFilter, String model2Filter) {
        String select = "select " +
                "year_of_production as yearOfProduction, " +
                "sum(mileage) as mileageSum, " +
                "count(*) as count " +
                "from offers";
        String where = " where 1=1 ";
        String groupBy = " GROUP by year_of_production";

        List<Object> args = new ArrayList<>();
        if (!Strings.isNullOrEmpty(makeFilter)) {
            where += " and make = ?";
            args.add(makeFilter);
        }
        if (!Strings.isNullOrEmpty(modelFilter)) {
            where += " and model = ?";
            args.add(modelFilter);
        }
        if (!Strings.isNullOrEmpty(model2Filter)) {
            where += " and model2 = ?";
            args.add(model2Filter);
        }
        return jdbcTemplate.query(select + where + groupBy, args.toArray(), (resultSet, i) -> {
            return MileageByProductionYearDTOBuilder.aMileageByProductionYearDTO()
                    .withYearOfProduction(resultSet.getInt("yearOfProduction"))
                    .withResultCount(resultSet.getLong("count"))
                    .withSumMileage(resultSet.getLong("mileageSum"))
                    .build()
                    ;
        });
    }
}
