package nl.hakktastic.leaseacarapi.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Lease Rate Calculation class.
 */
@Data
public class LeaseRateCalculation {

    private double leaseRate;
    private int mileage;
    private int duration;
    private CarBean car;
    private InterestRateBean interestRate;
    private CustomerBean customer;

    /**
     * Constructor.
     *
     * @param car          {@link CarBean} car object for which the lease rate should be calculated
     * @param interestRate {@link InterestRateBean} interest rate object for retrieving the
     *                     appropriate interest rate
     * @param customer     {@link CustomerBean}
     * @param mileage      annual mileage in kilometers
     * @param duration     contract duration in months
     */
    public LeaseRateCalculation(CarBean car, InterestRateBean interestRate, CustomerBean customer,
                                int mileage, int duration) {

        this.car = car;
        this.interestRate = interestRate;
        this.customer = customer;
        this.mileage = mileage;
        this.duration = duration;
    }

    /**
     * Calculate the lease rate.
     *
     * @return Returns the calculated lease rate
     */
    public Double calculate() {

        // calculate
        this.leaseRate = (((mileage / 12) * duration) / car.getNettPrice())
                + (((interestRate.getInterestRate() / 100) * car.getNettPrice()) / 12);

        // round to two decimals
        this.leaseRate =
                new BigDecimal(this.leaseRate).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

        return this.leaseRate;
    }
}
