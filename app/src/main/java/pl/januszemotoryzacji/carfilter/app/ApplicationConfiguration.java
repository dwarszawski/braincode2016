package pl.januszemotoryzacji.carfilter.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.januszemotoryzacji.service.OffersDownloadService;
import pl.januszemotoryzacji.service.OffersIdResolver;
import pl.januszmotoryzacji.service.dao.OfferWriter;

import java.util.HashMap;
import java.util.concurrent.Executors;

/**
 * Created by lbarc on 2016-03-19.
 */
@Configuration
public class ApplicationConfiguration {

    private static final String ACCESS_TOKEN = "e0a328fad7c0b64897e8beb574a14b580e8176dcc5a99138f7203b10be8adce8";

    public OffersIdResolver offersIdResolver() {
        OffersIdResolver offersIdResolver = new OffersIdResolver();
        offersIdResolver.setAccessToken(ACCESS_TOKEN);
        offersIdResolver.setExecutor(Executors.newFixedThreadPool(100));
        return offersIdResolver;
    }

    @Bean
    public OffersDownloadService offersDownloadService() {
        OffersDownloadService offersDownloadService = new OffersDownloadService();
        offersDownloadService.setAccessToken(ACCESS_TOKEN);
//        offersDownloadService.setMainCategoryId(4032); /// BMW
        offersDownloadService.setMainCategoryId(12438); /// BMW 7
        offersDownloadService.setOffersIdResolver(offersIdResolver());
        offersDownloadService.setOfferWriter(offerWriter());
        return offersDownloadService;
    }

    @Bean
    public OfferWriter offerWriter() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("url", "jdbc:h2:file:C:/db/test2;AUTO_SERVER=TRUE");
        map.put("user", "sa");
        map.put("password", "sa");
        map.put("driver", "org.h2.Driver");
        return new OfferWriter(map);
    }
}
