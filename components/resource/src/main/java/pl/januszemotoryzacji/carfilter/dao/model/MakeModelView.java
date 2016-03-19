package pl.januszemotoryzacji.carfilter.dao.model;

import lombok.Data;

/**
 * Created by lbarc on 2016-03-19.
 */
@Data
public class MakeModelView {
    private String make;
    private String model;
    private String model2;
    private int count;
}
