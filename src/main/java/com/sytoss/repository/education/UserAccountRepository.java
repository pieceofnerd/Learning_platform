package com.sytoss.repository.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    // need to delete this method
    @Query(nativeQuery = true, value = "select u.* from user_account u where role_id =?1")
    List<UserAccount> findUserAccountsByDeletedIsFalseAndRole(Long role);

    //this method should be deleted
    @Modifying
    @Query(value = "update user_account set role_id = :role where id= :id",
            nativeQuery = true)
    void changeUserAccountRole(@Param("role") Lookup role,
                               @Param("id") UserAccount userAccount);

    @Query("select u from UserAccount u where u.email = ?1 and u.deleted = false")
    UserAccount findUserAccountByEmailAndDeletedIsFalse(String email);

    @Query("select u from UserAccount u where upper(u.firstName) like upper(concat(?1, '%')) and upper(u.secondName) like upper(concat(?2, '%')) and u.deleted = false")
    List<UserAccount> findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCaseAndDeletedIsFalse(String firstName, String secondName);


    @Query("select u from UserAccount u where upper(u.firstName) like upper(concat(?1, '%')) and u.deleted = false")
    List<UserAccount> findAllByFirstNameStartingWithIgnoreCaseAndDeletedIsFalse(String firstName);


    @Query("select u from UserAccount u where upper(u.secondName) like upper(concat(?1, '%')) and u.deleted = false")
    List<UserAccount> findAllBySecondNameStartingWithIgnoreCaseAndDeletedIsFalse(String secondName);
}
