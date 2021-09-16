package com.sytoss.web.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String country;
    private String region;
    private String locality;
    private String postcode;
    private String streetName;
    private Integer houseNumber;
}