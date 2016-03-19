package pl.januszmotoryzacji.service.dao

import groovy.sql.Sql
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Log
import pl.januszemotoryzacji.carfilter.model.CarOffer

@CompileStatic
@Log
class OfferWriter {
    private final Map<String, String> configuration
    private final Sql sql

    OfferWriter(Map<String, String> configuration) {
        this.configuration = configuration
        this.sql = Sql.newInstance(configuration.get('url'), configuration.get('user'), configuration.get('password'), configuration.get('driver'))
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    public void insertOffer(List<CarOffer> offers) {
        sql.getConnection().setAutoCommit(false)

        offers.forEach {
            log.info("new offer to insert with id $it.identity")
            sql.executeInsert(insertSql(), [it.identity,
                                            it.version,
                                            it.make,
                                            it.model,
                                            it.model2,
                                            it.yearOfProduction,
                                            it.power,
                                            it.fuelType.toString(),
                                            it.province,
                                            it.mileage,
                                            it.price,
                                            new java.sql.Date(it.endDate.getTime())])
        }


        sql.getConnection().setAutoCommit(true)
    }

    private String insertSql() {
        return """INSERT INTO OFFERS (ID, VERSION, MAKE, MODEL, MODEL2, YEAR_OF_PRODUCTION, POWER, FUEL_TYPE, PROVINCE, MILEAGE, PRICE, END_DATE)
             VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"""
    }

}
