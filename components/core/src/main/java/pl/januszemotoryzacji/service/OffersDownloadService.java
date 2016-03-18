package pl.januszemotoryzacji.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.januszemotoryzacji.service.dto.AllegroCarOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lbarc on 2016-03-18.
 */
public class OffersDownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersDownloadService.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final String accessToken = "e0a328fad7c0b64897e8beb574a14b580e8176dcc5a99138f7203b10be8adce8";
    private OffersIdResolver offersIdResolver = new OffersIdResolver(executorService, accessToken);

    private int mainCategoryId = 4029;

    public void downloadOffers() throws ExecutionException, InterruptedException {
        List<Long> offerIds = offersIdResolver.loadOfferIds(mainCategoryId);

        List<Future<AllegroCarOffer>> futures = new ArrayList<Future<AllegroCarOffer>>();

        for (Long offerId : offerIds) {
            futures.add(executorService.submit(new OfferGrabber(new RestTemplate(), offerId, accessToken)));
        }

        for (Future<AllegroCarOffer> future : futures) {
            AllegroCarOffer allegroCarOffer = future.get();
            LOGGER.info("Offer : {}", allegroCarOffer);
        }


    }
}
