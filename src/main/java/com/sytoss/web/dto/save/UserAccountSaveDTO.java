package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountSaveDTO {

    private String firstName;

    private String secondName;

    private Date birthday;

    private String bio;

    private AddressSaveDTO address;

    private String email;

    private char[] password;

    private MediaSaveDTO photo;

}