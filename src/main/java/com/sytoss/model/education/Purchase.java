package com.sytoss.model.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.Course;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "student_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount student;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "purchase_status_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup purchaseStatus;

    @Column(name = "cost")
    private Double cost;

    @CreationTimestamp
    private Date purchaseDate;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getStudent() {
        return student;
    }

    public void setStudent(UserAccount student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lookup getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Lookup purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}