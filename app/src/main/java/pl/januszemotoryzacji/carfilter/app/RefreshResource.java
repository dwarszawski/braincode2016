package pl.januszemotoryzacji.carfilter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszemotoryzacji.service.OffersDownloadService;

import java.util.concurrent.ExecutionException;

/**
 * Created by lbarc on 2016-03-19.
 */
@RestController
@RequestMapping("/")
public class RefreshResource {

    @Autowired
    public OffersDownloadService offersDownloadService;

    @RequestMapping(method = RequestMethod.GET)
    public void refresh() throws ExecutionException, InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    offersDownloadService.downloadOffers();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
