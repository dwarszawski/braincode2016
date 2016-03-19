package pl.januszemotoryzacji.carfilter.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.januszemotoryzacji.carfilter.resource.dto.CategoryDTO;
import pl.januszemotoryzacji.carfilter.resource.dto.MileageByProductionYearDTO;
import pl.januszemotoryzacji.carfilter.service.StatsService;

import java.util.List;

/**
 * Created by lbarc on 2016-03-19.
 */
@RestController
@RequestMapping("/stat")
public class StatsResource {

    @Autowired
    private StatsService statsService;


    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<CategoryDTO> getCategories() {
        return statsService.fetchAllCategories();
    }


    @RequestMapping(path = "/mileageByProductionYear", method = RequestMethod.GET)
    public List<MileageByProductionYearDTO> getMileageGrupedByProductionYear(
            @RequestParam(name = "make", required = false) String makeFilter,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "model2", required = false) String model2) {

        return statsService.findMileageByProductionYear(makeFilter, model, model2);

    }
}
