package pl.januszemotoryzacji.service.scheduler

import groovy.util.logging.Slf4j
import pl.januszemotoryzacji.service.scheduler.task.FetchOfferDataTask

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@Slf4j
class DownloadScheduler {
    public static void schedule(List<Long> offers) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10)
        List<List<Long>> ranges = offers.collate(100)
        ranges.forEach() {
            executorService.scheduleAtFixedRate(new FetchOfferDataTask(it), 0, 5, TimeUnit.MINUTES)
        }
    }

}

