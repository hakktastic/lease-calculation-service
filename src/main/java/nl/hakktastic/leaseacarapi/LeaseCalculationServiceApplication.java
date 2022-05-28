package nl.hakktastic.leaseacarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("nl.hakktastic.leaseacarapi")
public class LeaseCalculationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaseCalculationServiceApplication.class, args);
    }

}
