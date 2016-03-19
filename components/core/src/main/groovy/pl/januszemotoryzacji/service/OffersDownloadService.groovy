package pl.januszemotoryzacji.service

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.januszemotoryzacji.service.scheduler.DownloadScheduler

import java.util.concurrent.ExecutionException
/**
 * Created by lbarc on 2016-03-18.
 */
@CompileStatic
public class OffersDownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersDownloadService.class);

    private OffersIdResolver offersIdResolver;
    private DownloadScheduler scheduler;

    public void downloadOffers(int mainCategoryId) throws ExecutionException, InterruptedException {
        List<Long> offerIds = offersIdResolver.loadOfferIds(mainCategoryId);
        scheduler.schedule(offerIds);
    }

    void setScheduler(DownloadScheduler scheduler) {
        this.scheduler = scheduler
    }

    void setOffersIdResolver(OffersIdResolver offersIdResolver) {
        this.offersIdResolver = offersIdResolver
    }
}
