package com.sytoss.model.education;


import com.sytoss.model.Media;
import com.sytoss.model.course.StudyGroup;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "study")
public class Study {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "student_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount student;

    @JoinColumn(name = "study_group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studyGroup;

    @Column(name = "progress", nullable = false)
    private Double progress;

    @Column(name = "assessment")
    private Double assessment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Media certificate;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Study() {
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

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public Media getCertificate() {
        return certificate;
    }

    public void setCertificate(Media certificate) {
        this.certificate = certificate;
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
}