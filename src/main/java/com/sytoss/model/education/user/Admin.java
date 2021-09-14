package com.sytoss.model.education.user;

import com.sytoss.model.education.UserAccount;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Admin extends UserAccount {
}
