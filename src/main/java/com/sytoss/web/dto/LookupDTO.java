package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class LookupDTO {

    private Long id;

    private Long lookupNameId;

    private String value;

}
