package com.sytoss.model.education;


import com.sytoss.model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private char[] password;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_activity")
    private Date lastActivity;

    @ManyToOne
    @JoinColumn(name = "image_path")
    private Media photo;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

}
