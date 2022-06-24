package nl.hakktastic.leaseacarapi.controller;


import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.service.LeaseCalculationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Lease Calculation Service.
 */
@RestController
@Slf4j
public class LeaseCalculationController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    LeaseCalculationService leaseCalculationService;

    @GetMapping(
            path = "/leaserates/car/{carId}/mileage/{mileage}/duration/{duration}/"
                    + "interestrate/{interestRateId}/customer/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateLeaseRate(@PathVariable int carId, @PathVariable int mileage,
                                                @PathVariable int duration, @PathVariable int interestRateId, @PathVariable int customerId) {

        var leaseRate = leaseCalculationService.calculateLeaseRate(carId,mileage,duration,interestRateId,customerId);

        logger.info("calculate lease rate --> starting calculation of lease rate");

        var status = (!Double.isNaN(leaseRate)) ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        logger.info("calculate lease rate --> response code -> {} ({})", status.value(), status.name());

        return new ResponseEntity<Double>(leaseRate, status);

    }

}
