package pl.januszmotoryzacji.service.dao

import groovy.sql.Sql
import pl.januszemotoryzacji.carfilter.model.CarOffer
import pl.januszemotoryzacji.carfilter.model.FuelType
import spock.lang.Specification

class OfferWriterTest extends Specification {

    Sql sql = Sql.newInstance([url: 'jdbc:h2:/tmp/h2', user: 'sa', password: 'sa', driver: 'org.h2.Driver'])

    def setup() {
        sql.execute('CREATE TABLE OFFERS (' +
                'ID INTEGER NOT NULL,' +
                ' VERSION VARCHAR(50),' +
                'YEAR_OF_PRODUCTION INTEGER,' +
                'POWER VARCHAR(50),' +
                'FUEL_TYPE VARCHAR(50),' +
                'PROVINCE VARCHAR(50),' +
                'MILEAGE INTEGER,' +
                'PRICE INTEGER)')
    }


    //ID, VERSION, YEAR_OF_PRODUCTION, POWER, FUEL_TYPE, PROVINCE, MILAGE, PRICE, END_DATE
    def 'Should insert offer into H2'() {
        given:
            CarOffer offer = new CarOffer(
                    identity: 1l,
                    version: 1,
                    make: '',
                    model: 'Volvo',
                    model2: 'Volvo2',
                    yearOfProduction: 2013,
                    fuelType: FuelType.BENZIN,
                    province: 'Warszawa',
                    mileage: 120000,
                    price: 1,
                    endDate: new Date()
            )
                List<CarOffer> offers = [offer]

        Map<String, String> configuration = [url: 'jdbc:h2:/tmp/h2', user: 'sa', password: 'sa', driver: 'org.h2.Driver']
        OfferWriter offerWriter = new OfferWriter(configuration)
        when:
            offerWriter.insertOffer(offers)
        then:
            sql.rows("SELECT * FROM OFFERS").size() ==1
    }

    def cleanup(){
        sql.execute("DROP TABLE OFFERS")
    }
}