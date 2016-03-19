package pl.januszemotoryzacji.service.utils;

import pl.januszemotoryzacji.carfilter.model.CarOffer;
import pl.januszemotoryzacji.carfilter.model.CarOfferBuilder;
import pl.januszemotoryzacji.service.dto.AllegroCarOffer;
import pl.januszemotoryzacji.service.dto.AllegroCategoryBreadcrumb;

public class CarOfferConverter {
    public static CarOffer convert(final AllegroCarOffer allegroCarOffer) {

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
