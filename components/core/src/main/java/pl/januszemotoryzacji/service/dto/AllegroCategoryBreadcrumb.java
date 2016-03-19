package pl.januszemotoryzacji.service.dto;

import lombok.Data;

@Data
public class AllegroCategoryBreadcrumb {

    private long id;
    private long parent;
    private boolean hasChildren;
    private String name;
}
