package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterUserAccountDTO {
    private boolean deleted;
    private String fullName;
    private String firstName;
    private String secondName;
}