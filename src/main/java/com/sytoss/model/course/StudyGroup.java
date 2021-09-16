package com.sytoss.model.course;

import com.sytoss.model.education.Study;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "study_group")
public class StudyGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @Column(name = "place_number")
    private Integer placeNumber;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToMany(mappedBy = "studyGroup",fetch = FetchType.LAZY)
    private List<Study> studies;

    @OneToMany(mappedBy = "studyGroup",fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public StudyGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", course=" + course +
                ", placeNumber=" + placeNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}