package pl.januszemotoryzacji.service.scheduler

import org.springframework.web.client.RestTemplate
import pl.januszemotoryzacji.carfilter.model.CarOffer
import pl.januszemotoryzacji.service.OfferGrabber
import pl.januszemotoryzacji.service.dto.AllegroCarOffer
import pl.januszemotoryzacji.service.utils.CarOfferConverter
import pl.januszmotoryzacji.service.dao.OfferWriter

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

class DownloadScheduler {
    private final String accessToken

    private OfferWriter offerWriter;

    DownloadScheduler(String accessToken) {
        this.accessToken = accessToken
    }

    public void schedule(List<Long> offerIds) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100)
        List<List<Long>> ranges = offerIds.collate(100)

        ranges.forEach() {
            List<AllegroCarOffer> batch= executorService.schedule(new OfferGrabber(new RestTemplate(), it, accessToken), 0, TimeUnit.MINUTES).get()
            List<CarOffer> batchOffer = batch.stream()
                    .filter{Optional.ofNullable(it).isPresent()}
                    .map{CarOfferConverter.convert(it)}.collect(Collectors.toList())

            offerWriter.insertOffer(batchOffer)
        }
    }

    void setOfferWriter(OfferWriter offerWriter) {
        this.offerWriter = offerWriter
    }

}

