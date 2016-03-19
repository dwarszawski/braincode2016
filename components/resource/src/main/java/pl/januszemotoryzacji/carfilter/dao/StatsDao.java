package pl.januszemotoryzacji.carfilter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.januszemotoryzacji.carfilter.dao.model.MakeModelView;
import pl.januszemotoryzacji.carfilter.dao.model.MakeModelViewBuilder;

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
}
