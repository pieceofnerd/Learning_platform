package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserAccountDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthday;
    private String email;
    private char[] password;
    private AddressDTO address;
    private Date lastActivity;
    private MediaDTO photo;
    private Date createdDate;
    private Date updatedDate;
}