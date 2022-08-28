package nl.hakktastic.leaseacarapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** Car Bean. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarBean implements Serializable {

  private int id;
  private String make;
  private String model;
  private String version;
  private int numberOfDoors;
  private double grossPrice;
  private double nettPrice;
  private int hp;
}
