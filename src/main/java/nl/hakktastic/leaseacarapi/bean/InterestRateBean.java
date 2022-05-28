package nl.hakktastic.leaseacarapi.bean;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.LocalDate;

/**
 * Interest Rate Bean.
 */
@Data
public class InterestRateBean {

    private int id;
    private double interestRate;
    private LocalDate startDate;

    /**
     * Default constructor.
     */
    public InterestRateBean() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           ID of interest rate object
     * @param interestRate interest rate
     * @param startDate    start date for the interest rate
     */
    public InterestRateBean(int id, double interestRate, LocalDate startDate) {

        this.id = id;
        this.interestRate = interestRate;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equation = false;

        if (obj instanceof InterestRateBean) {

            final InterestRateBean otherEntity = (InterestRateBean) obj;

            equation = new EqualsBuilder().appendSuper(super.equals(obj))
                    .append(this.getId(), otherEntity.getId()).isEquals();
        }

        return equation;
    }
}
