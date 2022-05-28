package nl.hakktastic.leaseacarapi.proxy;

import nl.hakktastic.leaseacarapi.bean.CustomerBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Proxy for retrieving Customer Entities from Customer-Service through Feign.
 */

@FeignClient(name = "customer-service", url = "http://customer-service:8081")
public interface CustomerServiceProxy {

    /**
     * Get Customer Entity by ID.
     *
     * @param id ID of Customer Entity
     * @return Returns a {@link CustomerBean} object
     */
    @GetMapping("/customer-service/customers/{id}")
    Optional<CustomerBean> getCustomerById(@PathVariable int id);

}
