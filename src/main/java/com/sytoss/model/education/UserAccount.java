package com.sytoss.model.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.Media;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "second_name")
    private String secondName;


    @Column(name = "birthday_date")
    private Date birthday;

    @Column
    private String bio;

    @Column
    @Email
    private String email;

    @Column
    private char[] password;

    @JoinColumn(name = "role_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup role;

    @JoinColumn(name = "address_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "last_activity")
    private Date lastActivity;

    @JoinColumn(name = "image_path")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media media;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToMany(mappedBy = "student")
    private List<Study> studies;


    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }
}
