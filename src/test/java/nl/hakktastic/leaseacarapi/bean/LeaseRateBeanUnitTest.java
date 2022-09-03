package nl.hakktastic.leaseacarapi.bean;

import nl.hakktastic.leaseacarapi.testdata.LeaseRateTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Unit Test for {@link LeaseRateBean}. */
public class LeaseRateBeanUnitTest {

  @Test
  public void givenValidArgs_whenGetLeaseRate_thenReturnCalculatedLeaseRate() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.TRUE);
    assertThat(optional).isNotNull().isPresent();
    assertThat(optional.get()).isEqualTo(LeaseRateTestData.LEASE_RATE_VALID);
  }

  @Test
  public void givenNoArgs_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(null)
            .customer(null)
            .interestRate(null)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(null)
            .interestRate(null)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCustomerOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(null)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(null)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(null)
            .customer(null)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarAndCustomerOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(null)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarAndInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(null)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCustomerAndInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(null)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional).isNotNull();
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenNoMileage_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional.isEmpty()).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void givenZeroMileage_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(0)
            .duration(LeaseRateTestData.duration)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional.isEmpty()).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void givenNoDuration_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenZeroDuration_whenGetLeaseRate_thenReturnEmptyOptional() {

    var leaseRateBean =
        LeaseRateBean.builder()
            .car(LeaseRateTestData.Car.CAR_OBJECT_VALID)
            .customer(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID)
            .interestRate(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID)
            .mileage(LeaseRateTestData.mileage)
            .duration(0)
            .build();

    var optional = leaseRateBean.getLeaseRate();

    assertThat(leaseRateBean.areRequiredParamsPresent()).isEqualTo(Boolean.FALSE);
    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }
}
