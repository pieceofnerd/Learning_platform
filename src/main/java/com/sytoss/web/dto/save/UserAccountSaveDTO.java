package com.sytoss.web.dto.save;

import com.sytoss.web.dto.TagDTO;
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
public class UserAccountSaveDTO {

    private String firstName;

    private String secondName;

    private Date birthday;

    private String bio;

    private AddressSaveDTO address;

    private String email;

    private char[] password;

    private MediaSaveDTO photo;

    private List<TagDTO> tags;

}