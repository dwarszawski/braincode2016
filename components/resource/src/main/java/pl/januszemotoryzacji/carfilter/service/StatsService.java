package pl.januszemotoryzacji.carfilter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszemotoryzacji.carfilter.dao.StatsDao;
import pl.januszemotoryzacji.carfilter.dao.model.MakeModelView;
import pl.januszemotoryzacji.carfilter.resource.dto.CategoryDTO;
import pl.januszemotoryzacji.carfilter.resource.dto.MileageByProductionYearDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbarc on 2016-03-19.
 */
@Service
public class StatsService {

    @Autowired
    private StatsDao statsDao;

    public List<CategoryDTO> fetchAllCategories() {

        List<MakeModelView> allMakeModels = statsDao.findAllMakeModels();

        List<CategoryDTO> categoryDTOs = new ArrayList<>();


        for (MakeModelView makeModelView : allMakeModels) {
            CategoryDTO make = null;
            for (CategoryDTO categoryDTO : categoryDTOs) {
                if (categoryDTO.getName().equalsIgnoreCase(makeModelView.getMake())) {
                    make = categoryDTO;
                }
            }
            if (make == null) {
                make = new CategoryDTO();
                make.setName(makeModelView.getMake());
                make.setSubcategories(new ArrayList<>());
                categoryDTOs.add(make);
            }
            make.setCount(make.getCount() + makeModelView.getCount());


            CategoryDTO model = null;
            for (CategoryDTO categoryDTO : make.getSubcategories()) {
                if (categoryDTO.getName().equalsIgnoreCase(makeModelView.getModel())) {
                    model = categoryDTO;
                }
            }

            if (model == null) {
                model = new CategoryDTO();
                model.setName(makeModelView.getModel());
                model.setSubcategories(new ArrayList<>());
                make.getSubcategories().add(model);
            }

            model.setCount(model.getCount() + makeModelView.getCount());


            CategoryDTO model2 = null;
            for (CategoryDTO categoryDTO : model.getSubcategories()) {
                if (categoryDTO.getName().equalsIgnoreCase(makeModelView.getModel2())) {
                    model2 = categoryDTO;
                }
            }

            if (model2 == null) {
                model2 = new CategoryDTO();
                model2.setName(makeModelView.getModel2());
                model2.setSubcategories(new ArrayList<>());
                model2.setCount(makeModelView.getCount());
            }
            model.getSubcategories().add(model2);

        }

        return categoryDTOs;
    }


    public List<MileageByProductionYearDTO> findMileageByProductionYear(String makeFilter, String modelFilter, String model2Filter) {
        return statsDao.findMileageByProductionYear(makeFilter, modelFilter, model2Filter);
    }
}
