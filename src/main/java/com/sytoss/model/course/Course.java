package com.sytoss.model.course;

import com.sytoss.model.Media;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "course_photo_id")
    @ManyToOne(optional = false)
    private Media coursePhoto;

    @Column(name = "recommended_literature")
    private String recommendedLiterature;

    @JoinColumn(name = "certificate_template_id")
    @ManyToOne(optional = false)
    private Media certificateTemplate;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "active")
    private Boolean active;

    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Topic> topics;

    @OneToMany(mappedBy = "course")
    private List<Price> prices;

    @OneToMany(mappedBy = "course")
    private List<CourseRating> courseRatings;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getCoursePhoto() {
        return coursePhoto;
    }

    public void setCoursePhoto(Media coursePhoto) {
        this.coursePhoto = coursePhoto;
    }

    public String getRecommendedLiterature() {
        return recommendedLiterature;
    }

    public void setRecommendedLiterature(String recommendedLiterature) {
        this.recommendedLiterature = recommendedLiterature;
    }

    public Media getCertificateTemplate() {
        return certificateTemplate;
    }

    public void setCertificateTemplate(Media certificateTemplate) {
        this.certificateTemplate = certificateTemplate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Topic> getActiveTopics() {
        List<Topic> activeTopics = new ArrayList<>();
        for (Topic topic : getTopics()) {
            if (topic.getActive())
                activeTopics.add(topic);
        }

        for (Topic activeTopic : activeTopics) {
            activeTopic.setLessonTemplates(activeTopic.getActiveLessonTemplates());
        }

        return activeTopics;
    }

    public List<CourseRating> getCourseRatings() {
        return courseRatings;
    }

    public void setCourseRatings(List<CourseRating> courseRatings) {
        this.courseRatings = courseRatings;
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
