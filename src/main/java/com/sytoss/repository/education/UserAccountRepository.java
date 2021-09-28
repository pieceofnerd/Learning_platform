package com.sytoss.repository.education;

import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select u from UserAccount u where u.email = ?1 and u.deleted = false")
    UserAccount findUserAccountByEmailAndDeletedIsFalse(String email);

    @Query("select u from UserAccount u where upper(u.firstName) like upper(concat(?1, '%')) and upper(u.secondName) like upper(concat(?2, '%')) and u.deleted = false")
    List<UserAccount> findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCaseAndDeletedIsFalse(String firstName, String secondName);


    @Query("select u from UserAccount u where upper(u.firstName) like upper(concat(?1, '%')) and u.deleted = false")
    List<UserAccount> findAllByFirstNameStartingWithIgnoreCaseAndDeletedIsFalse(String firstName);


    @Query("select u from UserAccount u where upper(u.secondName) like upper(concat(?1, '%')) and u.deleted = false")
    List<UserAccount> findAllBySecondNameStartingWithIgnoreCaseAndDeletedIsFalse(String secondName);
}
