package pl.januszemotoryzacji.carfilter.dao.model;

import lombok.Data;

@Data
public class MileageByYearOfProductionView {

    private int yearOfProduction;
    private long sumMileage;
    private long resultCount;
}
