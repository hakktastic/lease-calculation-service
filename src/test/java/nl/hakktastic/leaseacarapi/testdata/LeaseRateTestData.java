package nl.hakktastic.leaseacarapi.testdata;

import nl.hakktastic.leaseacarapi.bean.CarBean;
import nl.hakktastic.leaseacarapi.bean.CustomerBean;
import nl.hakktastic.leaseacarapi.bean.InterestRateBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Test Data for Lease Rate Service. */
public final class LeaseRateTestData {

  public static final Double LEASE_RATE_VALID = 572.34;

  public final class Car {

    public static final int ID_VALID_LAND_ROVER = 4677;
    public static final String MAKE_VALID_LAND_ROVER = "Land Rover";
    public static final String MODEL_VALID_DISCOVERY = "Discovery";
    public static final String VERSION_VALID_DISCOVERY = "2.0 Si4 HSE Luxury";
    public static final int NR_OF_DOORS_VALID_5 = 5;
    public static double GROSS_PRICE_VALID_LAND_ROVER = 120205;
    public static double NETT_PRICE_VALID_LAND_ROVER = 68680.99;
    public static int HP_VALID_LAND_ROVER = 300;

    public static final CarBean CAR_OBJECT_VALID =
        new CarBean()
            .builder()
            .id(ID_VALID_LAND_ROVER)
            .make(MAKE_VALID_LAND_ROVER)
            .model(MODEL_VALID_DISCOVERY)
            .version(VERSION_VALID_DISCOVERY)
            .numberOfDoors(NR_OF_DOORS_VALID_5)
            .grossPrice(GROSS_PRICE_VALID_LAND_ROVER)
            .nettPrice(NETT_PRICE_VALID_LAND_ROVER)
            .hp(HP_VALID_LAND_ROVER)
            .build();
  }

  public final class Customer {

    public static final int ID_VALID = 1001;
    public static final String NAME_VALID = "Harry Snel";
    public static final String STREET_NAME_VALID = "Musuemplein";
    public static final int HOUSE_NR_VALID = 25;
    public static final String ZIP_CODE_VALID = "1001AS";
    public static final String PLACE_NAME_VALID = "AMSTERDAM";
    public static final String EMAIL_VALID = "valid@gmail.com";
    public static Integer PHONE_NUMBER_VALID = 1026142315;

    public static final CustomerBean CUSTOMER_OBJECT_VALID =
        new CustomerBean()
            .builder()
            .id(ID_VALID)
            .name(NAME_VALID)
            .street(STREET_NAME_VALID)
            .houseNumber(HOUSE_NR_VALID)
            .zipcode(ZIP_CODE_VALID)
            .place(PLACE_NAME_VALID)
            .email(EMAIL_VALID)
            .phoneNumber(PHONE_NUMBER_VALID)
            .build();
  }

  public final class InterestRate {

    public static final LocalDate START_DATE_VALID_EXISTING_2014_05_01 =
        LocalDate.parse("2014-05-01", DateTimeFormatter.ISO_DATE);
    public static final Double INTEREST_RATE_VALID_10 = 10.0;
    public static final Integer INTEREST_RATE_OBJECT_ID_1001 = 1001;

    public static final InterestRateBean INTEREST_RATE_OBJECT_VALID =
        InterestRateBean.builder()
            .id(INTEREST_RATE_OBJECT_ID_1001)
            .startDate(START_DATE_VALID_EXISTING_2014_05_01)
            .interestRate(INTEREST_RATE_VALID_10)
            .build();
  }
}
