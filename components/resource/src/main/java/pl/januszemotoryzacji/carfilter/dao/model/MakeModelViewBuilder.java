package pl.januszemotoryzacji.carfilter.dao.model;

/**
 * Created by lbarc on 2016-03-19.
 */
public class MakeModelViewBuilder {
    private String make;
    private String model;
    private String model2;
    private int count;

    private MakeModelViewBuilder() {
    }

    public static MakeModelViewBuilder aMakeModelView() {
        return new MakeModelViewBuilder();
    }

    public MakeModelViewBuilder withMake(String make) {
        this.make = make;
        return this;
    }

    public MakeModelViewBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public MakeModelViewBuilder withModel2(String model2) {
        this.model2 = model2;
        return this;
    }

    public MakeModelViewBuilder withCount(int count) {
        this.count = count;
        return this;
    }

    public MakeModelViewBuilder but() {
        return aMakeModelView().withMake(make).withModel(model).withModel2(model2).withCount(count);
    }

    public MakeModelView build() {
        MakeModelView makeModelView = new MakeModelView();
        makeModelView.setMake(make);
        makeModelView.setModel(model);
        makeModelView.setModel2(model2);
        makeModelView.setCount(count);
        return makeModelView;
    }
}
