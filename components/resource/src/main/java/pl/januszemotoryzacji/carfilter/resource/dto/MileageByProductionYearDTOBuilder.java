package pl.januszemotoryzacji.carfilter.resource.dto;

/**
 * Created by lbarc on 2016-03-19.
 */
public class MileageByProductionYearDTOBuilder {
    private int yearOfProduction;
    private long sumMileage;
    private long resultCount;

    private MileageByProductionYearDTOBuilder() {
    }

    public static MileageByProductionYearDTOBuilder aMileageByProductionYearDTO() {
        return new MileageByProductionYearDTOBuilder();
    }

    public MileageByProductionYearDTOBuilder withYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
        return this;
    }

    public MileageByProductionYearDTOBuilder withSumMileage(long sumMileage) {
        this.sumMileage = sumMileage;
        return this;
    }

    public MileageByProductionYearDTOBuilder withResultCount(long resultCount) {
        this.resultCount = resultCount;
        return this;
    }

    public MileageByProductionYearDTOBuilder but() {
        return aMileageByProductionYearDTO().withYearOfProduction(yearOfProduction).withSumMileage(sumMileage).withResultCount(resultCount);
    }

    public MileageByProductionYearDTO build() {
        MileageByProductionYearDTO mileageByProductionYearDTO = new MileageByProductionYearDTO();
        mileageByProductionYearDTO.setYearOfProduction(yearOfProduction);
        mileageByProductionYearDTO.setSumMileage(sumMileage);
        mileageByProductionYearDTO.setResultCount(resultCount);
        return mileageByProductionYearDTO;
    }
}
