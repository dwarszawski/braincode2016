package pl.januszemotoryzacji.carfilter.resource.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private String name;
    private long count;
    private List<CategoryDTO> subcategories;
}
