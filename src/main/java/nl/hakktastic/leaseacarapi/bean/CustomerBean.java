package nl.hakktastic.leaseacarapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean implements Serializable {

  private int id;
  private String name;
  private String street;
  private int houseNumber;
  private String zipcode;
  private String place;
  private String email;
  private int phoneNumber;
}
