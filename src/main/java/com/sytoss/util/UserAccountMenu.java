package com.sytoss.util;

import com.sytoss.controller.UserAccountController;
import com.sytoss.mapper.AddressMapper;
import com.sytoss.mapper.MediaMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.AddressDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.save.AddressSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import com.sytoss.web.dto.update.UserAccountUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountMenu {
    private final UserAccountController userAccountController;
    private final UserAccountRepository userAccountRepository;
    private final AddressMapper addressMapper;
    private final MediaMapper mediaMapper;

    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Register user",
                "2. Send CV",
                "3. Update user",
                "4. Delete user"
        );

        long userId;
        UserAccount user;
        UserAccountSaveDTO userAccountSaveDTO;
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
                if (scanInt("If you want to add photo(optional), enter - 1") == 1) {
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
            case 2:
                String cv = scanLine("Add your CV - ");
                MediaSaveDTO mediaSaveDTO = new MediaSaveDTO();
                mediaSaveDTO.setMediaPath(cv);

                userAccountController.saveCV(mediaSaveDTO);
                break;
            case 3:
                userId = scanInt("Write user id to update - ");
                user = userAccountRepository.findOne(userId);
                printUser(user);
                userUpdate(getUserUpdate(user));
                break;
            case 4:
                userId = scanInt("Write user id to update - ");
                user = userAccountRepository.findOne(userId);
                UserAccountUpdateDTO userUpdate = getUserUpdate(user);
                userAccountController.deleteUserAccount(userUpdate);
                break;
        }
    }


    private void userUpdate(UserAccountUpdateDTO userDTO) throws Exception {
        switch (scanInt("Write field which want to update - ")) {
            case 1:
                userDTO.setFirstName(scanLine("Write new first name - "));
                break;
            case 2:
                userDTO.setSecondName(scanLine("Write new second name - "));
                break;
            case 3:
                userDTO.setBirthday(scanDate("Write new birthday - "));
                break;
            case 4:
                userDTO.setBio(scanLine("Write new bio - "));
                break;
            case 5:
                printClassName(AddressDTO.class.getSimpleName());
                AddressDTO address = new AddressDTO();
                address.setCountry(scanLine("Write country - "));
                address.setRegion(scanLine("Write region - "));
                address.setLocality(scanLine("Write locality - "));
                address.setPostcode(scanLine("Write postcode(optional) -"));
                address.setStreetName(scanLine("Write street name - "));
                address.setHouseNumber(scanInt("Write houes number"));
                userDTO.setAddress(address);
                break;
            case 6:
                //TODO PHOTO
                break;
            case 7:
                userDTO.setEmail(scanLine("Writ new e-mail - "));
                break;
            case 8:
                String oldPassword = scanLine("Write your old password - ");
                String newPassword = scanLine("Write new password - ");
                userAccountController.resetPassword(userDTO, oldPassword.toCharArray(), newPassword.toCharArray());
                break;

        }
        userAccountController.updateUserAccount(userDTO);
    }

    private void printUser(UserAccount user) {
        printClassName(user.getClass().getSimpleName());
        printField("1. First name", user.getFirstName());
        printField("2. Second name", user.getSecondName());
        printField("3. Birthday", user.getBirthday());
        printField("4. Bio", user.getBio());
        printField("5. Address", user.getAddress());
        printField("6. Photo", user.getPhoto());
        printField("7. Email", user.getEmail());
        printField("8. Password", "*****");
    }

    private UserAccountUpdateDTO getUserUpdate(UserAccount user) {
        UserAccountUpdateDTO userUpdate = new UserAccountUpdateDTO();
        userUpdate.setId(user.getId());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setSecondName(user.getSecondName());
        userUpdate.setBirthday(user.getBirthday());
        userUpdate.setBio(user.getBio());
        userUpdate.setAddress(addressMapper.toDTO(user.getAddress()));
        userUpdate.setPhoto(mediaMapper.toDTO(user.getPhoto()));
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        return userUpdate;
    }

}
