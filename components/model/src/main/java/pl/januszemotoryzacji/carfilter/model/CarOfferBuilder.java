package pl.januszemotoryzacji.carfilter.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lbarc on 2016-03-19.
 */
public class CarOfferBuilder {
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

    private CarOfferBuilder() {
    }

    public static CarOfferBuilder aCarOffer() {
        return new CarOfferBuilder();
    }

    public CarOfferBuilder withIdentity(long identity) {
        this.identity = identity;
        return this;
    }

    public CarOfferBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    public CarOfferBuilder withMake(String make) {
        this.make = make;
        return this;
    }

    public CarOfferBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarOfferBuilder withModel2(String model2) {
        this.model2 = model2;
        return this;
    }

    public CarOfferBuilder withYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
        return this;
    }

    public CarOfferBuilder withPower(int power) {
        this.power = power;
        return this;
    }

    public CarOfferBuilder withFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public CarOfferBuilder withProvince(String province) {
        this.province = province;
        return this;
    }

    public CarOfferBuilder withMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public CarOfferBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CarOfferBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public CarOfferBuilder but() {
        return aCarOffer().withIdentity(identity).withVersion(version).withMake(make).withModel(model).withModel2(model2).withYearOfProduction(yearOfProduction).withPower(power).withFuelType(fuelType).withProvince(province).withMileage(mileage).withPrice(price).withEndDate(endDate);
    }

    public CarOffer build() {
        CarOffer carOffer = new CarOffer();
        carOffer.setIdentity(identity);
        carOffer.setVersion(version);
        carOffer.setMake(make);
        carOffer.setModel(model);
        carOffer.setModel2(model2);
        carOffer.setYearOfProduction(yearOfProduction);
        carOffer.setPower(power);
        carOffer.setFuelType(fuelType);
        carOffer.setProvince(province);
        carOffer.setMileage(mileage);
        carOffer.setPrice(price);
        carOffer.setEndDate(endDate);
        return carOffer;
    }
}
