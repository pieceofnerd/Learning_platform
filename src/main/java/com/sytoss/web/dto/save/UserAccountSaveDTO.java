package com.sytoss.web.dto.save;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserAccountSaveDTO {
    private String firstName;
    private String secondName;
    private Date birthday;
    private String email;
    private char[] password;
    private AddressSaveDTO address;
    private MediaSaveDTO photo;
}