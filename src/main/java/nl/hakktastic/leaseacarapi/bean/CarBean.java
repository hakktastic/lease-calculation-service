package nl.hakktastic.leaseacarapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Car Bean. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarBean {

  private int id;
  private String make;
  private String model;
  private String version;
  private int numberOfDoors;
  private double grossPrice;
  private double nettPrice;
  private int hp;
}
