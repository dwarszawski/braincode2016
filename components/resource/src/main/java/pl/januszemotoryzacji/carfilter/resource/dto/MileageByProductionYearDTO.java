package pl.januszemotoryzacji.carfilter.resource.dto;

import lombok.Data;

@Data
public class MileageByProductionYearDTO {

    private int yearOfProduction;
    private long sumMileage;
    private long resultCount;
}
