package pl.januszemotoryzacji.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllegroOffersListRequest {
    private int category;
    private String access_token;
    private int offset;
    private int limit;
}
