package nl.hakktastic.leaseacarapi.controller;


import nl.hakktastic.leaseacarapi.bean.CarBean;
import nl.hakktastic.leaseacarapi.bean.CustomerBean;
import nl.hakktastic.leaseacarapi.bean.InterestRateBean;
import nl.hakktastic.leaseacarapi.bean.LeaseRateCalculation;
import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Rest controller for Lease Calculation Service.
 */
@RestController
public class LeaseCalculationController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CarServiceProxy carServiceProxy;
    @Autowired
    private InterestRateServiceProxy interestRateProxy;
    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    /**
     * Calculate lease rate.
     *
     * @param carId          ID of car object <em>(retrieved from car-service)</em>
     * @param mileage        annual mileage in kilometers
     * @param duration       lease contract duration in months
     * @param interestRateId ID of Interest Rate object <em>(retrieved from
     *                       interest-calculation-service)</em>
     * @param customerId     ID of the customer
     * @return Returns a {@link ResponseEntity} containing a {@link LeaseRateCalculation} object
     */
    @GetMapping(
            path = "/leaserates/car/{carId}/mileage/{mileage}/duration/{duration}/"
                    + "interestrate/{interestRateId}/customer/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> calculateLeaseRate(@PathVariable int carId, @PathVariable int mileage,
                                                @PathVariable int duration, @PathVariable int interestRateId, @PathVariable int customerId) {

        final ResponseEntity<LeaseRateCalculation> responseEntity;

        // get car object from car-service
        final Optional<CarBean> optionalCarBean = carServiceProxy.getCarById(carId);

        // get interest rate object from interest-rate-calculation-service
        final Optional<InterestRateBean> optionalInterestRateBean = interestRateProxy.getInterestById(interestRateId);

        final Optional<CustomerBean> optionalCustomerBean = customerServiceProxy.getCustomerById(customerId);

        // check if containers are present
        if (optionalCarBean.isPresent() && optionalInterestRateBean.isPresent()
                && optionalCustomerBean.isPresent()) {

            // create lease calculation object
            LeaseRateCalculation leaseRateCalculation = new LeaseRateCalculation(optionalCarBean.get(),
                    optionalInterestRateBean.get(), optionalCustomerBean.get(), mileage, duration);

            // calculate lease rate
            leaseRateCalculation.calculate();

            responseEntity = new ResponseEntity<>(leaseRateCalculation, HttpStatus.OK);

            logger.info("Calculate Lease Rate: \n --> Response Code -> {} \n --> Response -> \n {} ",
                    responseEntity.getStatusCodeValue(), responseEntity.getBody());

        } else {

            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

}
