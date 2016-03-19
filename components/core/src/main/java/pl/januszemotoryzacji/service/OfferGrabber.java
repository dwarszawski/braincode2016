package pl.januszemotoryzacji.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.januszemotoryzacji.service.dto.AllegroCarOffer;

import java.util.concurrent.Callable;

public class OfferGrabber implements Callable<AllegroCarOffer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferGrabber.class);

    private RestTemplate restTemplate;
    private long offerId;
    private String accessToken;

    public OfferGrabber(RestTemplate restTemplate, long offerId, String accessToken) {
        this.restTemplate = restTemplate;
        this.offerId = offerId;
        this.accessToken = accessToken;
    }

    public AllegroCarOffer call() throws Exception {
        try {
            return restTemplate.getForEntity("https://api.natelefon.pl/v2/allegro/offers/" + offerId +
                    "?" +
                    "access_token=" + accessToken, AllegroCarOffer.class).getBody();
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            return null;
        }
    }
}
