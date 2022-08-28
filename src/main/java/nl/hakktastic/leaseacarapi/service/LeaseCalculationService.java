package nl.hakktastic.leaseacarapi.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Service class for managing the calculation of the lease rate. */
@Service
@Slf4j
public class LeaseCalculationService {

  @Autowired private CarServiceProxy carServiceProxy;

  @Autowired private InterestRateServiceProxy interestRateProxy;

  @Autowired private CustomerServiceProxy customerServiceProxy;

  public double calculateLeaseRate(
      int carId, int mileage, int duration, int interestRateId, int customerId) {

    var carBean = this.carServiceProxy.getCarById(carId).orElseGet(() -> null);
    var interestRateBean =
        this.interestRateProxy.getInterestById(interestRateId).orElseGet(() -> null);
    var customerBean = this.customerServiceProxy.getCustomerById(customerId).orElseGet(() -> null);

    Validate.notNull(carBean, "unable to retreive car entity with ID: {}", carId);
    Validate.notNull(
        interestRateBean, "unable to retreive interest rate entity with ID: {}", interestRateId);
    Validate.notNull(customerBean, "unable to retrieve customer entity with ID: {}", customerId);

    log.info("Mileage --> {}", mileage);
    log.info("Duration --> {}", duration);
    log.info("Car entity --> {}", carBean);
    log.info("Interest Rate entity --> {}", interestRateBean);
    log.info("Customer entity --> {}", customerBean);

    var leaseRate =
        (((mileage / 12) * duration) / carBean.getNettPrice())
            + (((interestRateBean.getInterestRate() / 100) * carBean.getNettPrice()) / 12);

    log.info("Lease Rate --> {}", leaseRate);

    return new BigDecimal(leaseRate).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
  }
}
