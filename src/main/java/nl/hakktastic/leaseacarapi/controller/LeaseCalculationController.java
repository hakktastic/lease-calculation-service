package nl.hakktastic.leaseacarapi.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.service.LeaseCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

/** Rest controller for Lease Calculation Service. */
@RestController
@Slf4j
public class LeaseCalculationController {

  @Autowired LeaseCalculationService leaseCalculationService;

  @GetMapping(
      path =
          "/leaserates/car/{carId}/mileage/{mileage}/duration/{duration}/"
              + "interestrate/{interestRateId}/customer/{customerId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BigDecimal> calculateLeaseRate(
      @PathVariable @Valid int carId,
      @PathVariable @Valid int mileage,
      @PathVariable @Valid int duration,
      @PathVariable @Valid int interestRateId,
      @PathVariable @Valid int customerId) {

    var leaseRate =
        leaseCalculationService.calculateLeaseRate(
            carId, mileage, duration, interestRateId, customerId);

    var status = (leaseRate.isPresent()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

    log.info("calculate lease rate --> response code -> {} ({})", status.value(), status.name());

    return new ResponseEntity<BigDecimal>(leaseRate.orElse(BigDecimal.ZERO), status);
  }
}
