package com.sytoss.repository.education;

import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select u from UserAccount u where u.email = ?1")
    UserAccount findUserAccountByEmail(String email);



    @Query("select u from UserAccount u where upper(u.firstName) like upper(concat(?1, '%')) and upper(u.secondName) like upper(concat(?2, '%'))")
    List<UserAccount> findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCase(String firstName, String secondName);
}
