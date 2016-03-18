package pl.januszemotoryzacji.carfilter.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CarOffer {

    private long identity;
    private int version;
    private String make;
    private String model;
    private String model2;
    private int yearOfProduction;
    private int power;
    private FuelType fuelType;
    private String province;
    private int mileage;
    private BigDecimal price;
    private Date endDate;

}
