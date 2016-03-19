package pl.januszemotoryzacji.service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class AllegroCarOffer {

    private long id;
    private List<AllegroCarOfferAttribute> attributes;
    private Date endingTime;
    private AllegroCarOfferLocation location;
    private AllegroCarOfferPrice prices;
}
