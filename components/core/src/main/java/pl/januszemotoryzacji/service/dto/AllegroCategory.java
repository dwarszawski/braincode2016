package pl.januszemotoryzacji.service.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by lbarc on 2016-03-19.
 */
@Data
public class AllegroCategory {

    private List<AllegroCategoryBreadcrumb> breadcrumbs;
}

