package pl.januszemotoryzacji.service.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllegroOffersResponse {

    private int count;
    private List<AllegroOffersList> offers = new ArrayList<AllegroOffersList>();
}
