package nl.hakktastic.leaseacarapi.proxy;

import nl.hakktastic.leaseacarapi.bean.CarBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


/**
 * Proxy for retrieving Car Entities from Car-Service through Feign.
 */

@FeignClient(name = "car-service", url = "http://car-service:8082")
public interface CarServiceProxy {

    /**
     * Get Car object.
     *
     * @param id Id of the Car object
     * @return Returns a {@link CarBean} object containing car data
     */
    @GetMapping("/car-service/cars/{id}")
    Optional<CarBean> getCarById(@PathVariable int id);
}
