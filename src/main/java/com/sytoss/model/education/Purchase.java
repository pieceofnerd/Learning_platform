package com.sytoss.model.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.StudyGroup;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "student_id")
    @ManyToOne(optional = false)
    private UserAccount student;

    @JoinColumn(name = "study_group_id")
    @ManyToOne(optional = false)
    private StudyGroup studyGroup;

    @JoinColumn(name = "purchase_status_id")
    @ManyToOne(optional = false)
    private Lookup purchaseStatus;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "purchase_date")
    private Date purchaseDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

    public Purchase() {
    }

    public Purchase(UserAccount student, StudyGroup studyGroup) {
        this.student = student;
        this.studyGroup = studyGroup;
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

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }


    public Lookup getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Lookup purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}