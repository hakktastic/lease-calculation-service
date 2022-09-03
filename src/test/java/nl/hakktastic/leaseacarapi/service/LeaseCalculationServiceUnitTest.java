package nl.hakktastic.leaseacarapi.service;

import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import nl.hakktastic.leaseacarapi.testdata.LeaseRateTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/** Unit Test for {@link LeaseCalculationService}. */
@ExtendWith(MockitoExtension.class)
public class LeaseCalculationServiceUnitTest {

  @InjectMocks private LeaseCalculationService service;

  @Mock private CarServiceProxy carServiceProxy;

  @Mock private InterestRateServiceProxy interestRateProxy;

  @Mock private CustomerServiceProxy customerServiceProxy;

  @Test
  public void givenValidArgs_whenGetLeaseRate_thenReturnCalculatedLeaseRate() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional).isNotNull().isPresent();
    assertThat(optional.get()).isEqualTo(LeaseRateTestData.LEASE_RATE_VALID);
  }

  @Test
  public void givenNoArgs_whenGetLeaseRate_thenReturnEmptyOptional() {

    var optional = service.calculateLeaseRate(0, 0, 0, 0, 0);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(LeaseRateTestData.Car.ID_VALID_LAND_ROVER, 0, 0, 0, 0);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCustomerOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional = service.calculateLeaseRate(0, 0, 0, 0, LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            0, 0, 0, LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001, 0);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarAndCustomerOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));

    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            0,
            0,
            0,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCarAndInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));

    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            0,
            0,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            0);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenCustomerAndInterestRateOnly_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            0,
            0,
            0,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenNoMileage_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            0,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }

  @Test
  public void givenNoDuration_whenGetLeaseRate_thenReturnEmptyOptional() {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(interestRateProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));

    var optional =
        service.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            0,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(optional.isPresent()).isEqualTo(Boolean.FALSE);
  }
}
