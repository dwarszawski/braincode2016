package pl.januszemotoryzacji.service.scheduler

import spock.lang.Specification

class DownloadSchedulerTest extends Specification {
    def 'should schedule fetching offers'(){
        when:
            DownloadScheduler.schedule([1..200000])
        then:
            //TODO should check if data is in database
            assert true

    }

}
