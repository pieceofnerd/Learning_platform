package com.sytoss.web.dto.update;

import com.sytoss.web.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountUpdateDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthday;
    private String bio;
    private AddressDTO address;
    private MediaDTO photo;
    private boolean deleted;
    private String email;
    private char[] password;
}