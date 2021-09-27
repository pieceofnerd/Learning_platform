package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AddressSaveDTO {

    private String country;

    private String region;

    private String locality;

    private String postcode;

    private String streetName;

    private Integer houseNumber;

}