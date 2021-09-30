package com.sytoss.util;

import com.sytoss.controller.UserAccountController;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.save.AddressSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class UserAccountMenu {
    private final UserAccountController userAccountController;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Register user"
        );

        long studentId;
        long studyGroupId;
        Student student;
        StudyGroup studyGroup;
        UserAccountSaveDTO userAccountSaveDTO;
        StudyGroupDTO studyGroupDTO;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                userAccountSaveDTO = new UserAccountSaveDTO();
                String fName = scanLine("Write first name - ");
                String sName = scanLine("Write second name - ");
                Date birthday = scanDate("Write birthday(optional) - ");
                String bio = scanLine("Write bio(optional) - ");
                if (scanInt("If you want to add your address(optional), enter - 1") == 1) {

                    AddressSaveDTO address = new AddressSaveDTO();
                    printClassName(address.getClass().getSimpleName());
                    address.setCountry(scanLine("Write country - "));
                    address.setRegion(scanLine("Write region - "));
                    address.setLocality(scanLine("Write locality - "));
                    address.setPostcode(scanLine("Write post code(optional) - "));
                    address.setStreetName(scanLine("Write street name - "));
                    address.setHouseNumber(scanInt("Write house number - "));
                    userAccountSaveDTO.setAddress(address);
                }
                if (scanInt("If you want to add photo(optional), enter - 1") == 1){
                    MediaSaveDTO mediaSaveDTO = new MediaSaveDTO();
                    mediaSaveDTO.setMediaPath(scanLine("Write image path - "));
                    userAccountSaveDTO.setPhoto(mediaSaveDTO);
                }
                String email = scanLine("Write email - ");
                String password = scanLine("Write password - ");
                userAccountSaveDTO.setFirstName(fName);
                userAccountSaveDTO.setSecondName(sName);
                userAccountSaveDTO.setBio(bio);
                userAccountSaveDTO.setBirthday(birthday);
                userAccountSaveDTO.setEmail(email);
                userAccountSaveDTO.setPassword(password.toCharArray());

                userAccountController.registerUserAccount(userAccountSaveDTO);
                break;
        }
    }
}
