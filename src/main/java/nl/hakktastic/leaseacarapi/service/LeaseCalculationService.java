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

  public Optional<BigDecimal> calculateLeaseRate(
      int carId, int mileage, int duration, int interestRateId, int customerId) {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(this.carServiceProxy.getCarById(carId).orElse(null))
            .customer(this.customerServiceProxy.getCustomerById(customerId).orElse(null))
            .interestRate(this.interestRateProxy.getInterestById(interestRateId).orElse(null))
            .build();

    log.info("Lease Rate --> {}", leaseRateBean.toString());

    return leaseRateBean.getLeaseRate();
  }
}
