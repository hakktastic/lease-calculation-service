package nl.hakktastic.leaseacarapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.OptionalDouble;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaseRateBean implements Serializable {

  private CarBean car;
  private CustomerBean customer;
  private InterestRateBean interestRate;
  private Double leaseRate;
  private int mileage;
  private int duration;

  /**
   * @return Returns TRUE if car-, customer- and interest rate entities have been set.
   */
  public boolean areRequiredParamsPresent() {

    return (this.car != null && car.getId() != 0)
        && (this.customer != null && this.customer.getId() != 0)
        && (this.interestRate != null && this.interestRate.getId() != 0);
  }

  public OptionalDouble getLeaseRate() {

    if (areRequiredParamsPresent()) {

      var leaseRate =
          (((this.mileage / 12) * this.duration) / this.car.getNettPrice())
              + (((this.interestRate.getInterestRate() / 100) * this.car.getNettPrice()) / 12);

      var result = new BigDecimal(leaseRate).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

      return OptionalDouble.of(result);
    }

    return OptionalDouble.empty();
  }
}
