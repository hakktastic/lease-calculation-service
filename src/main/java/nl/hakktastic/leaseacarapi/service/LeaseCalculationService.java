package nl.hakktastic.leaseacarapi.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    if (mileage < 1) {

      log.warn("Aborting calculation because no mileage > 1 was provided");
      return Optional.empty();
    }

    if (duration < 1) {
      log.warn("Aborting calculation because no duration > 1 was provided");
      return Optional.empty();
    }

    var car = carServiceProxy.getCarById(carId);
    var customer = customerServiceProxy.getCustomerById(customerId);
    var interestRate = interestRateProxy.getInterestById(interestRateId);

    if (!car.isPresent()) {

      log.warn(
          "Aborting calculation because service was unable to retrieve Car info for ID:{}", carId);
      return Optional.empty();
    }

    if (!interestRate.isPresent()) {

      log.warn(
          "Aborting calculation because service was unable to retrieve Interest Rate info for ID:{}",
          interestRateId);
      return Optional.empty();
    }

    if (!customer.isPresent()) {

      log.warn(
          "Unable to retrieve Customer info for ID:{}. Continuing calculation because this info is not mandatory",
          customerId);
    }

    log.info("Car information for provided id {}:{}", carId, car);
    log.info("Customer information for provided id {}:{}", customerId, customer);
    log.info("Interest Rate information for provided id {}:{}", interestRateId, interestRate);

    var leaseRate =
        (((mileage / 12) * duration) / car.get().getNettPrice())
            + (((interestRate.get().getInterestRate() / 100) * car.get().getNettPrice()) / 12);

    var result = new BigDecimal(leaseRate).setScale(2, RoundingMode.HALF_DOWN);

    return Optional.ofNullable(result);
  }
}
