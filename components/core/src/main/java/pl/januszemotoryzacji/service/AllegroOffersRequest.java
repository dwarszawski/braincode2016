package pl.januszemotoryzacji.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllegroOffersRequest {
    private int category;
    private String access_token;
    private int offset;
    private int limit;
}
