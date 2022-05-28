package nl.hakktastic.leaseacarapi.bean;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Car Bean.
 */
@Data
public class CarBean {

    private int id;
    private String make;
    private String model;
    private String version;
    private int numberOfDoors;
    private double grossPrice;
    private double nettPrice;
    private int hp;

    /**
     * Default Constructor.
     */
    public CarBean() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id            ID of the car object
     * @param make          make of the car object
     * @param model         model of the car object
     * @param version       version of the car object
     * @param numberOfDoors # doors of the car object
     * @param grossPrice    gross price of the car object
     * @param nettPrice     nett price of the car object
     * @param hp            horse power of the car object
     */
    public CarBean(int id, String make, String model, String version, int numberOfDoors,
                   double grossPrice, double nettPrice, int hp) {
        super();
        this.id = id;
        this.make = make;
        this.model = model;
        this.version = version;
        this.numberOfDoors = numberOfDoors;
        this.grossPrice = grossPrice;
        this.nettPrice = nettPrice;
        this.hp = hp;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equation = false;

        if (obj instanceof CarBean) {

            final CarBean otherEntity = (CarBean) obj;

            equation = new EqualsBuilder().appendSuper(super.equals(obj))
                    .append(this.getId(), otherEntity.getId()).isEquals();
        }

        return equation;
    }
}
