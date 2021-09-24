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
    @ManyToOne(optional = false)
    private UserAccount student;

    @JoinColumn(name = "study_group_id")
    @ManyToOne(optional = false)
    private StudyGroup studyGroup;

    @Column(name = "progress")
    private Double progress = 0.0d;

    @Column(name = "assessment")
    private Double assessment;

    @ManyToOne
    private Media certificate;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "\nStudy{" +
                "\n  id=" + id +
                "\n, studyGroup=" + studyGroup.getId() +
                "\n, progress=" + progress +
                "\n, assessment=" + assessment +
                "\n, certificate=" + certificate +
                "\n}";
    }
}