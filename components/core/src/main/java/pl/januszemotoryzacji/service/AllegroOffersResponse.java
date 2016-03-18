package pl.januszemotoryzacji.service;


import lombok.Data;

import java.util.List;

@Data
public class AllegroOffersResponse {

    private int count;
    private List<AllegroOffer> offers;
}
