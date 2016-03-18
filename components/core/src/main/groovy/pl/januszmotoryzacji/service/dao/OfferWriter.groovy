package pl.januszmotoryzacji.service.dao

import groovy.sql.Sql
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import pl.januszemotoryzacji.carfilter.model.CarOffer

@CompileStatic
class OfferWriter {
    private final Map<String, String> configuration
    private final Sql sql

    OfferWriter(Map<String, String> configuration) {
        this.configuration =configuration
        this.sql = Sql.newInstance(configuration.get('url'), configuration.get('user'), configuration.get('password'), configuration.get('driver'))
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    public void insertOffer(List<CarOffer> offers) {
       // sql.getDataSource().getConnection().setAutoCommit(false)

       offers.forEach {
            sql.executeInsert("INSERT INTO OFFERS (ID, VERSION, YEAR_OF_PRODUCTION, POWER, FUEL_TYPE, PROVINCE, MILEAGE, PRICE)" +
                    "VALUES (${it.identity}, '${it.version}', ${it.yearOfProduction}, ${it.power}, '${it.fuelType.toString()}', '${it.province}', ${it.mileage}, ${it.price})")
        }

       // sql.getDataSource().getConnection().setAutoCommit(true)
    }
}
