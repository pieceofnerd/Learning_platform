package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class UserAccountSaveDTO {
    private String firstName;
    private String secondName;

}