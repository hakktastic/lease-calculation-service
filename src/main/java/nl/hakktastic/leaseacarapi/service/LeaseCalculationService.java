package nl.hakktastic.leaseacarapi.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Service class for managing the calculation of the lease rate. */
@Service
@Slf4j
public class LeaseCalculationService {

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

  @Autowired private CarServiceProxy carServiceProxy;

  @Autowired private InterestRateServiceProxy interestRateProxy;

  @Autowired private CustomerServiceProxy customerServiceProxy;

  public double calculateLeaseRate(
      int carId, int mileage, int duration, int interestRateId, int customerId) {

    var carBean = this.carServiceProxy.getCarById(carId).orElseGet(() -> null);
    var interestRateBean = this.interestRateProxy.getInterestById(interestRateId).orElseGet(() -> null);
    var customerBean = this.customerServiceProxy.getCustomerById(customerId).orElseGet(() -> null);

    Validate.notNull(carBean, "unable to retreive car entity with ID: {}", carId);
    Validate.notNull(
        interestRateBean, "unable to retreive interest rate entity with ID: {}", interestRateId);
    Validate.notNull(customerBean, "unable to retrieve customer entity with ID: {}", customerId);

      this.logger.info("Mileage --> {}", mileage);
      this.logger.info("Duration --> {}", duration);
      this.logger.info("Car entity --> {}", carBean);
      this.logger.info("Interest Rate entity --> {}", interestRateBean);
      this.logger.info("Customer entity --> {}", customerBean);

    var leaseRate =
        (((mileage / 12) * duration) / carBean.getNettPrice())
            + (((interestRateBean.getInterestRate() / 100) * carBean.getNettPrice()) / 12);

      this.logger.info("Lease Rate --> {}", leaseRate);

    return new BigDecimal(leaseRate).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
  }
}
