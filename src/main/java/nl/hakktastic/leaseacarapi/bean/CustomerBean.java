package nl.hakktastic.leaseacarapi.bean;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Data
public class CustomerBean {

    private int id;
    private String name;
    private String street;
    private int houseNumber;
    private String zipcode;
    private String place;
    private String email;
    private int phoneNumber;


    /**
     * Default Constructor.
     */
    public CustomerBean() {
    }

    /**
     * Constructor.
     *
     * @param id          ID of Customer Entity
     * @param name        first and last name of user
     * @param street      the street of the user
     * @param houseNumber the house number of the user
     * @param zipcode     the zip code of the user
     * @param place       the place of residence of the user
     * @param email       the email address of the user
     * @param phoneNumber the phone number of the user
     */
    public CustomerBean(int id, String name, String street, int houseNumber, String zipcode,
                        String place, String email, int phoneNumber) {

        this.id = id;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.place = place;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equation = false;

        if (obj instanceof CustomerBean) {

            final CustomerBean otherEntity = (CustomerBean) obj;

            equation = new EqualsBuilder().appendSuper(super.equals(obj))
                    .append(this.getId(), otherEntity.getId()).isEquals();
        }

        return equation;
    }
}
