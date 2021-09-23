package com.sytoss.web.dto;

import com.sytoss.model.LookupName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupDTO {

    private Long id;

    private LookupNameDTO lookupName;

    private String value;

}
