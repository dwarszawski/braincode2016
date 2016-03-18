package pl.januszemotoryzacji.service.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by lbarc on 2016-03-18.
 */
@Data
public class AllegroCarOfferAttribute {
    private String name;
    private List<String> values;
}
