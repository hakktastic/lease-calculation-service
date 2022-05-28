package nl.hakktastic.leaseacarapi.proxy;

import nl.hakktastic.leaseacarapi.bean.InterestRateBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Proxy for retrieving Interest Rate Entities from Interest-Rate-Calculation-Service through Feign.
 */

@FeignClient(name = "interest-service", url = "http://interest-rate-service:8083")
public interface InterestRateServiceProxy {

    /**
     * Get Interest object based on the ID.
     *
     * @param id ID of the Interest Object
     * @return Returns a {@link InterestRateBean} containing interest rate data
     */
    @GetMapping("/interest-rate-service/interestrates/{id}")
    Optional<InterestRateBean> getInterestById(@PathVariable int id);

}
