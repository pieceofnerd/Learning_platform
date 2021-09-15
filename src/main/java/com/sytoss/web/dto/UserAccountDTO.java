package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class UserAccountDTO {
    private Long id;
    private String firstName;
    private String secondName;

}