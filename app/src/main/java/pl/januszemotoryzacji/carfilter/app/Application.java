package pl.januszemotoryzacji.carfilter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
