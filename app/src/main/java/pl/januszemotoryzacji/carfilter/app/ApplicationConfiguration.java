package pl.januszemotoryzacji.carfilter.app;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.januszemotoryzacji.service.OffersDownloadService;
import pl.januszemotoryzacji.service.OffersIdResolver;
import pl.januszemotoryzacji.service.scheduler.DownloadScheduler;
import pl.januszmotoryzacji.service.dao.OfferWriter;

import java.util.HashMap;
import java.util.concurrent.Executors;

/**
 * Created by lbarc on 2016-03-19.
 */
@Configuration
public class ApplicationConfiguration {

    private static final String ACCESS_TOKEN = "2eb2afb8928586058a9474b91e6194f71fe2d50862af7b6d7376f2b74b2bab8c";

    public OffersIdResolver offersIdResolver() {
        OffersIdResolver offersIdResolver = new OffersIdResolver();
        offersIdResolver.setAccessToken(ACCESS_TOKEN);
        offersIdResolver.setExecutor(Executors.newFixedThreadPool(10));
        return offersIdResolver;
    }

    @Bean
    public OffersDownloadService offersDownloadService() {
        OffersDownloadService offersDownloadService = new OffersDownloadService();
        offersDownloadService.setScheduler(new DownloadScheduler(ACCESS_TOKEN));
        offersDownloadService.setOffersIdResolver(offersIdResolver());
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

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        // return new CorsFilter(source);
        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
