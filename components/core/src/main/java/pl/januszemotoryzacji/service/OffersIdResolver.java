package pl.januszemotoryzacji.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.januszemotoryzacji.service.dto.AllegroOffersList;
import pl.januszemotoryzacji.service.dto.AllegroOffersListRequest;
import pl.januszemotoryzacji.service.dto.AllegroOffersResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class OffersIdResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersIdResolver.class);
    private ExecutorService executor;
    private String accessToken;


    public OffersIdResolver(ExecutorService executor, String accessToken) {
        this.executor = executor;
        this.accessToken = accessToken;
    }

    public List<Long> loadOfferIds(int categoryId) {

        try {
            LOGGER.info("Loading offer ids");

            ArrayList<Long> ids = new ArrayList<Long>();

            Future<AllegroOffersResponse> submit = executor.submit(new RequestCallable(new RestTemplate(), categoryId, accessToken, 0, 1));

            AllegroOffersResponse offers = submit.get();

            int count = offers.getCount();

            LOGGER.info("Total count : {}", count);

            List<Future<AllegroOffersResponse>> futures = new ArrayList<Future<AllegroOffersResponse>>();
            for (int i = 0; i < 20000; i += 1000) {
                futures.add(executor.submit(new RequestCallable(new RestTemplate(), categoryId, accessToken, i, 1000)));
            }

            AtomicInteger loaded = new AtomicInteger();
            for (Future<AllegroOffersResponse> future : futures) {
                AllegroOffersResponse response = future.get();
                for (AllegroOffersList allegroOffersList : response.getOffers()) {
                    ids.add(allegroOffersList.getId());
                    loaded.incrementAndGet();
                }
                LOGGER.info("Loadded {} ids", loaded.get());

            }


            return ids;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class RequestCallable implements Callable<AllegroOffersResponse> {

        private RestTemplate restTemplate;
        private int categoryId;
        private String accessToken;
        private int offset;
        private int limit;

        public AllegroOffersResponse call() throws Exception {
            return restTemplate.postForEntity("https://api.natelefon.pl/v2/allegro/offers?" +
                            "access_token=" + accessToken,
                    new AllegroOffersListRequest(categoryId, accessToken, offset, limit),
                    AllegroOffersResponse.class).getBody();
        }
    }
}
