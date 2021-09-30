package com.sytoss.model.course;

import com.sytoss.model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    private Media coursePhoto;

    @Column(name = "recommended_literature")
    private String recommendedLiterature;

    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "certificate_template_id")
    private Media certificateTemplate;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "active")
    private Boolean active;

    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false)
    private Category category;


    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Topic> topics;

    @OneToMany(
            mappedBy = "course",
            cascade = {CascadeType.ALL}
    )
    private List<Price> prices;

    @OneToMany(
            mappedBy = "course",
            cascade = {CascadeType.ALL}
    )
    private List<CourseRating> courseRatings;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

    public void setPrices(List<Price> prices) {
        this.prices = prices;
        for (Price price : this.prices) {
            price.setCourse(this);
        }
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
        for (Topic topic : this.topics) {
            topic.setCourse(this);
        }
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

}
