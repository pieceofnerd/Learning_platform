package com.sytoss.model.course;

import com.sytoss.model.Media;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @JoinColumn(name = "course_photo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media coursePhoto;

    @Column(name = "recommended_literature")
    private String recommendedLiterature;

    @JoinColumn(name = "certificate_template_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media certificateTemplate;

    @Column
    private Double rating;

    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    @OneToMany(mappedBy = "course")
    private List<Price> prices;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Promotion promotion;

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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
