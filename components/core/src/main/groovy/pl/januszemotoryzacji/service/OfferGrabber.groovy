package pl.januszemotoryzacji.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.januszemotoryzacji.service.dto.AllegroCarOffer;

import java.util.concurrent.Callable;

public class OfferGrabber implements Callable<List<AllegroCarOffer>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferGrabber.class);

    private RestTemplate restTemplate;
    private List<Long> offerIds;
    private String accessToken;


    public OfferGrabber(RestTemplate restTemplate, List<Long> offerIds, String accessToken) {
        this.restTemplate = restTemplate;
        this.offerIds = offerIds;
        this.accessToken = accessToken;
    }

    public List<AllegroCarOffer> call() throws Exception {
        List<AllegroCarOffer> offers = new ArrayList<>()
        try {
            offerIds.forEach {
                offers.add(restTemplate.getForEntity("https://api.natelefon.pl/v2/allegro/offers/" + offerId +
                        "?" +
                        "access_token=" + accessToken, AllegroCarOffer.class).getBody())
            }
            return offers
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            return offers;
        }
    }
}
