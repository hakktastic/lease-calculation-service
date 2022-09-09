package nl.hakktastic.leaseacarapi.controller;

import nl.hakktastic.leaseacarapi.proxy.CarServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.CustomerServiceProxy;
import nl.hakktastic.leaseacarapi.proxy.InterestRateServiceProxy;
import nl.hakktastic.leaseacarapi.testdata.LeaseRateTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LeaseCalculationControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CarServiceProxy carServiceProxy;
  @MockBean private CustomerServiceProxy customerServiceProxy;
  @MockBean private InterestRateServiceProxy interestRateServiceProxy;

  private static final String URL_TEMPLATE =
      "/leaserates/car/{carId}/mileage/{mileage}/duration/{duration}/"
          + "interestrate/{interestRateId}/customer/{customerId}";

  @Test
  public void givenNoArgs_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL_TEMPLATE, 0, 0, 0, 0, 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenOnlyCarId_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE, LeaseRateTestData.Car.ID_VALID_LAND_ROVER, 0, 0, 0, 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenOnlyMileage_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL_TEMPLATE, 0, LeaseRateTestData.mileage, 0, 0, 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenOnlyDuration_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(URL_TEMPLATE, 0, 0, LeaseRateTestData.duration, 0, 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenOnlyInterestRateId_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    0,
                    0,
                    0,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenOnlyCustomerId_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE, 0, 0, 0, 0, LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenAllArgs_whenCalculateLeaseRate_thenReturnOK() throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenNoCustomerId_whenCalculateLeaseRate_thenReturnOK() throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt())).thenReturn(Optional.empty());
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenNoInterestRateId_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    0,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenNoDuration_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    0,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenNoMileage_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    0,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenNoCarId_whenCalculateLeaseRate_thenReturnNotFound() throws Exception {

    when(carServiceProxy.getCarById(anyInt())).thenReturn(Optional.empty());
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    0,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenCustomerServiceProxyReturnsNull_whenCalculateLeaseRate_thenReturnNotFound()
      throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt())).thenReturn(Optional.empty());
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInterestRateServiceProxyReturnsNull_whenCalculateLeaseRate_thenReturnNotFound()
      throws Exception {

    when(carServiceProxy.getCarById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Car.CAR_OBJECT_VALID));
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenCarServiceProxyReturnsNull_whenCalculateLeaseRate_thenReturnNotFound()
      throws Exception {

    when(carServiceProxy.getCarById(anyInt())).thenReturn(Optional.empty());
    when(customerServiceProxy.getCustomerById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.Customer.CUSTOMER_OBJECT_VALID));
    when(interestRateServiceProxy.getInterestById(anyInt()))
        .thenReturn(Optional.ofNullable(LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_VALID));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    URL_TEMPLATE,
                    LeaseRateTestData.Car.ID_VALID_LAND_ROVER,
                    LeaseRateTestData.mileage,
                    LeaseRateTestData.duration,
                    LeaseRateTestData.InterestRate.INTEREST_RATE_OBJECT_ID_1001,
                    LeaseRateTestData.Customer.ID_VALID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
