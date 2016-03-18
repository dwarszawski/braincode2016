package pl.januszemotoryzacji.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OffersIdResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersIdResolver.class);
    private ExecutorService executor = Executors.newFixedThreadPool(20);


    private String accessToken = "7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new OffersIdResolver().loadOfferIds(4029);
    }

    public List<Long> loadOfferIds(int categoryId) throws ExecutionException, InterruptedException {

        LOGGER.info("Loading offer ids");

        ArrayList<Long> ids = new ArrayList<Long>();

        Future<AllegroOffersResponse> submit = executor.submit(new RequestCallable(new RestTemplate(), categoryId, accessToken, 0, 1));

        AllegroOffersResponse offers = submit.get();

        int count = offers.getCount();

        LOGGER.info("Total count : {}", count);

        List<Future<AllegroOffersResponse>> futures = new ArrayList<Future<AllegroOffersResponse>>();
        for (int i = 0; i < count; i += 1000) {
            futures.add(executor.submit(new RequestCallable(new RestTemplate(), categoryId, accessToken, i, 1000)));
        }

        AtomicInteger loaded = new AtomicInteger();
        for (Future<AllegroOffersResponse> future : futures) {
            AllegroOffersResponse response = future.get();
            for (AllegroOffer allegroOffer : response.getOffers()) {
                ids.add(allegroOffer.getId());
                loaded.incrementAndGet();
            }

        }


        return ids;
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
                            "access_token=7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b",
                    new AllegroOffersRequest(categoryId, accessToken, offset, limit),
                    AllegroOffersResponse.class).getBody();
        }
    }
}
