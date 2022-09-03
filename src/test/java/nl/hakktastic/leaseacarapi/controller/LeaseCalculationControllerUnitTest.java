package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.service.LeaseCalculationService;
import nl.hakktastic.leaseacarapi.testdata.LeaseRateTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaseCalculationControllerUnitTest {

  @InjectMocks LeaseCalculationController controller;
  @Mock LeaseCalculationService service;

  @Test
  public void givenNoArgs_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity = controller.calculateLeaseRate(0, 0, 0, 0, 0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenOnlyCarId_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity =
        controller.calculateLeaseRate(LeaseRateTestData.Car.ID_VALID_LAND_ROVER, 0, 0, 0, 0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenOnlyMileage_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity = controller.calculateLeaseRate(0, LeaseRateTestData.mileage, 0, 0, 0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenOnlyDuration_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity = controller.calculateLeaseRate(0, 0, LeaseRateTestData.duration, 0, 0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenOnlyInterestRateId_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity =
        controller.calculateLeaseRate(
            0, 0, 0, LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001, 0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenOnlyCustomerId_whenCalculateLeaseRate_thenReturnNotFound() {

    var responseEntity =
        controller.calculateLeaseRate(0, 0, 0, 0, LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenAllArgs_whenCalculateLeaseRate_thenReturnOK() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.of(LeaseRateTestData.LEASE_RATE_VALID));

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(LeaseRateTestData.LEASE_RATE_VALID);
  }

  @Test
  public void givenNoCustomerId_whenCalculateLeaseRate_thenReturnOK() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.of(LeaseRateTestData.LEASE_RATE_VALID));

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            0);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(LeaseRateTestData.LEASE_RATE_VALID);
  }

  @Test
  public void givenNoInterestRateId_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            0,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenNoDuration_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            0,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenNoMileage_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            0,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenNoCarId_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            0,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  // *

  @Test
  public void givenInvalidCustomerId_whenCalculateLeaseRate_thenReturnOK() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.of(LeaseRateTestData.LEASE_RATE_VALID));

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            2525);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(LeaseRateTestData.LEASE_RATE_VALID);
  }

  @Test
  public void givenInvalidInterestRateId_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            999999,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void givenInvalidCarId_whenCalculateLeaseRate_thenReturnNotFound() {

    when(service.calculateLeaseRate(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
        .thenReturn(Optional.empty());

    var responseEntity =
        controller.calculateLeaseRate(
            25382510,
            LeaseRateTestData.mileage,
            LeaseRateTestData.duration,
            LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
            LeaseRateTestData.Customer.ID_VALID);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo(BigDecimal.ZERO);
  }
}
