package nl.hakktastic.leaseacarapi.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.bean.LeaseRateBean;
import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/** Service class for managing the calculation of the lease rate. */
@Service
@Slf4j
public class LeaseCalculationService {

  @Autowired private CarServiceProxy carServiceProxy;

  @Autowired private InterestRateServiceProxy interestRateProxy;

  @Autowired private CustomerServiceProxy customerServiceProxy;

  /**
   * Calculate the lease rate.
   *
   * @param carId the id of the car object
   * @param mileage the yearly mileage to be driven
   * @param duration the duration of the leease period in months
   * @param interestRateId the id of the interest rate object
   * @param customerId the id of the customer
   * @return Returns an Optional containing the lease rate if all values are provided, otherwise
   *     returns {@code Optional.empty()}.
   */
  public Optional<BigDecimal> calculateLeaseRate(
      int carId, int mileage, int duration, int interestRateId, int customerId) {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(this.carServiceProxy.getCarById(carId).orElse(null))
            .customer(this.customerServiceProxy.getCustomerById(customerId).orElse(null))
            .interestRate(this.interestRateProxy.getInterestById(interestRateId).orElse(null))
            .duration(duration)
            .mileage(mileage)
            .build();

    log.info("Lease Rate --> {}", leaseRateBean.toString());

    return leaseRateBean.getLeaseRate();
  }
}
