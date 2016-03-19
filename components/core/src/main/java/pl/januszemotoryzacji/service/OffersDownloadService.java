package pl.januszemotoryzacji.service;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import pl.januszemotoryzacji.carfilter.model.CarOffer;
import pl.januszemotoryzacji.carfilter.model.CarOfferBuilder;
import pl.januszemotoryzacji.service.dto.AllegroCarOffer;
import pl.januszemotoryzacji.service.dto.AllegroCategoryBreadcrumb;
import pl.januszmotoryzacji.service.dao.OfferWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

/**
 * Created by lbarc on 2016-03-18.
 */
public class OffersDownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersDownloadService.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Setter
    private String accessToken;
    @Setter
    private OffersIdResolver offersIdResolver;
    @Setter
    private OfferWriter offerWriter;

    public void downloadOffers(int mainCategoryId) throws ExecutionException, InterruptedException {
        List<Long> offerIds = offersIdResolver.loadOfferIds(mainCategoryId);

        LOGGER.info("LOADDED {} offerIds", offerIds.size());

        List<Future<AllegroCarOffer>> futures = new ArrayList<>();

        for (Long offerId : offerIds) {
            futures.add(executorService.submit(new OfferGrabber(new RestTemplate(), offerId, accessToken)));
        }


        int counter = 0;

        for (Future<AllegroCarOffer> future : futures) {
            AllegroCarOffer allegroCarOffer = future.get();
            if (allegroCarOffer != null) {
                LOGGER.debug("Offer : {}", allegroCarOffer);
                counter++;

                try {
                    CarOffer converted = convert(allegroCarOffer);
                    offerWriter.insertOffer(Arrays.asList(converted));
                    LOGGER.info("Offer with {} {} {} inserted",
                            converted.getMake(),
                            converted.getModel(),
                            converted.getModel2());
                } catch (Exception ex) {
                    LOGGER.warn(ex.getMessage());
                }
            }
        }

        LOGGER.info("LOADED {} offers.", counter);


    }

    private CarOffer convert(final AllegroCarOffer allegroCarOffer) {

        int mileage = Integer.valueOf(allegroCarOffer.getAttributes().stream().filter(allegroCarOfferAttribute ->
                allegroCarOfferAttribute.getName().equalsIgnoreCase("Przebieg [km]")).findFirst().get().getValues().get(0));

        int power = Integer.valueOf(allegroCarOffer.getAttributes().stream().filter(allegroCarOfferAttribute ->
                allegroCarOfferAttribute.getName().equalsIgnoreCase("Moc [KM]")).findFirst().get().getValues().get(0));

        int yearOfProduction = Integer.valueOf(allegroCarOffer.getAttributes().stream().filter(allegroCarOfferAttribute ->
                allegroCarOfferAttribute.getName().equalsIgnoreCase("Rok produkcji")).findFirst().get().getValues().get(0));

        AllegroCategoryBreadcrumb make = allegroCarOffer.getCategories().getBreadcrumbs().stream().filter(allegroCategoryBreadcrumb ->
                allegroCategoryBreadcrumb.getParent() == 4029).findFirst().get();

        AllegroCategoryBreadcrumb model = allegroCarOffer.getCategories().getBreadcrumbs().stream().filter(allegroCategoryBreadcrumb ->
                allegroCategoryBreadcrumb.getParent() == make.getId()).findFirst().get();

        AllegroCategoryBreadcrumb model2 = allegroCarOffer.getCategories().getBreadcrumbs().stream().filter(allegroCategoryBreadcrumb ->
                allegroCategoryBreadcrumb.getParent() == model.getId()).findFirst().get();

        return CarOfferBuilder.aCarOffer()
                .withIdentity(allegroCarOffer.getId())
                .withEndDate(allegroCarOffer.getEndingTime())
                .withMake(make.getName())
                .withModel(model.getName())
                .withModel2(model2.getName())
                .withMileage(mileage)
                .withPower(power)
                .withPrice(allegroCarOffer.getPrices().getBuyNow())
                .withYearOfProduction(yearOfProduction)
                .build();


    }

}
