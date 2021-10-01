package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressSaveDTO {

    private String country;

    private String region;

    private String locality;

    private String postcode;

    private String streetName;

    private Integer houseNumber;

}