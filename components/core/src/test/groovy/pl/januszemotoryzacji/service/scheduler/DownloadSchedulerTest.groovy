package pl.januszemotoryzacji.service.scheduler

import org.junit.Ignore
import spock.lang.Specification

@Ignore
class DownloadSchedulerTest extends Specification {
    def 'should schedule fetching offers'(){
        when:
            DownloadScheduler scheduler = new DownloadScheduler()
            scheduler.schedule([1..200000].asList())
        then:
            //TODO should check if data is in database
            assert true

    }

}
