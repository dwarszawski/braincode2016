package pl.januszemotoryzacji.carfilter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    // 12438 BMW7
    // 4032 BMW7

    @RequestMapping(path = "{categoryId}", method = RequestMethod.GET)
    public void refresh(@PathVariable("categoryId") Integer categoryId) throws ExecutionException, InterruptedException {
        new Thread(() -> {
            try {
                offersDownloadService.downloadOffers(categoryId);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
