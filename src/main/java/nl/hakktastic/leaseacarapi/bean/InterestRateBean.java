package nl.hakktastic.leaseacarapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/** Interest Rate Bean. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestRateBean implements Serializable {

  private int id;
  private double interestRate;
  private LocalDate startDate;
}
