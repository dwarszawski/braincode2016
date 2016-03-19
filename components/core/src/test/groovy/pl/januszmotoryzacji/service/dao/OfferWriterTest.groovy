package pl.januszmotoryzacji.service.dao

import groovy.sql.Sql
import pl.januszemotoryzacji.carfilter.model.CarOffer
import pl.januszemotoryzacji.carfilter.model.FuelType
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class OfferWriterTest extends Specification {

    Sql sql = Sql.newInstance([url: 'jdbc:h2:/tmp/h2', user: 'sa', password: 'sa', driver: 'org.h2.Driver'])

    def setup() {
        sql.execute('CREATE TABLE OFFERS (' +
                'ID BIGINT NOT NULL,' +
                'VERSION INTEGER,' +
                'MAKE VARCHAR(50),' +
                'MODEL VARCHAR(50),' +
                'MODEL2 VARCHAR(50),' +
                'YEAR_OF_PRODUCTION INTEGER NOT NULL,' +
                'POWER VARCHAR(50) NOT NULL,' +
                'FUEL_TYPE VARCHAR(50) NOT NULL,' +
                'PROVINCE VARCHAR(50) NOT NULL,' +
                'MILEAGE INTEGER NOT NULL,' +
                'PRICE BIGINT NOT NULL,' +
                'END_DATE DATE) ')
    }

    def 'should write offers to datastore'() {
        given:
        CarOffer firstOffer = baseCarOffer()
        CarOffer secondOffer = baseCarOffer()
        secondOffer.with {
            model = 'BMW'
            model2 = '320'
            mileage = 200000l
            fuelType = FuelType.DIESEL
        }

        List<CarOffer> offers = [firstOffer, secondOffer]

        Map<String, String> configuration = [url: 'jdbc:h2:/tmp/h2', user: 'sa', password: 'sa', driver: 'org.h2.Driver']
        OfferWriter offerWriter = new OfferWriter(configuration)
        when:
        offerWriter.insertOffer(offers)
        then:
        def result = sql.rows("SELECT * FROM OFFERS")
        result.size() == 2
        new CarOffer(
                identity: result.get(1).get('id'),
                version: result.get(1).('version'),
                make: result.get(1).('make'),
                model: result.get(1).('model'),
                model2: result.get(1).('model2'),
                yearOfProduction: result.get(1).('year_of_production'),
                fuelType: FuelType.valueOf(result.get(1).('fuel_type')),
                province: result.get(1).('province'),
                mileage: result.get(1).('mileage'),
                price: result.get(1).('price'),
                endDate: result.get(1).('end_date')
        ) == secondOffer
    }

    def cleanup() {
        sql.execute("DROP TABLE OFFERS")
    }

    private CarOffer baseCarOffer() {
        return new CarOffer(
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
                endDate: Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant())
        )
    }
}