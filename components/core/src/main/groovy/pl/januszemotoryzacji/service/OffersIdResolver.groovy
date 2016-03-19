package pl.januszemotoryzacji.service

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import pl.januszemotoryzacji.service.dto.AllegroOffersList
import pl.januszemotoryzacji.service.dto.AllegroOffersListRequest
import pl.januszemotoryzacji.service.dto.AllegroOffersResponse

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger

@CompileStatic
public class OffersIdResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersIdResolver.class);

    private String accessToken;

    private ExecutorService executor

    public List<Long> loadOfferIds(int categoryId) {

        try {
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

            LOGGER.info("Futures submitted");

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

    @CompileStatic
    private static class RequestCallable implements Callable<AllegroOffersResponse> {

        private static final Logger LOGGER = LoggerFactory.getLogger(RequestCallable.class);

        private RestTemplate restTemplate;
        private int categoryId;
        private String accessToken;
        private int offset;
        private int limit;

        RequestCallable(RestTemplate restTemplate, int categoryId, String accessToken, int offset, int limit) {
            this.restTemplate = restTemplate
            this.categoryId = categoryId
            this.accessToken = accessToken
            this.offset = offset
            this.limit = limit
        }

        public AllegroOffersResponse call() throws Exception {
            try {
                return (AllegroOffersResponse)restTemplate.postForEntity("https://api.natelefon.pl/v2/allegro/offers?" +
                        "access_token=" + accessToken,
                        new AllegroOffersListRequest(categoryId, accessToken, offset, limit),
                        AllegroOffersResponse.class).getBody();
            } catch (Exception ex) {
                LOGGER.warn(ex.getMessage());
                return new AllegroOffersResponse();
            }
        }
    }


    void setAccessToken(String accessToken) {
        this.accessToken = accessToken
    }

    void setExecutor(ExecutorService executor) {
        this.executor = executor
    }

}
