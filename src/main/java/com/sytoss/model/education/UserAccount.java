package com.sytoss.model.education;


import com.sytoss.model.course.Lesson;
import com.sytoss.model.Lookup;
import com.sytoss.model.Media;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_id")
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "birthday_date")
    private Date birthday;

//    @Column(name = "bio")
//    private String bio;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private char[] password;

    @JoinColumn(name = "address_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "last_activity")
    private Date lastActivity;

    @JoinColumn(name = "image_path")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media photo;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate;
//
//    @OneToMany(mappedBy = "mentor")
//    private List<Lesson> lessons;
//
//    @OneToMany(mappedBy = "student")
//    private List<Study> studies;
//
//    @OneToMany(mappedBy = "author")
//    private List<Homework> homeworks;

    public UserAccount() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Media getPhoto() {
        return photo;
    }

    public void setPhoto(Media photo) {
        this.photo = photo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", address=" + address +
                ", lastActivity=" + lastActivity +
                ", photo=" + photo +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
