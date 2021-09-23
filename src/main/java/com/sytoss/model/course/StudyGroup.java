package com.sytoss.model.course;

import com.sytoss.model.education.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "study_group")
public class StudyGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false)
    private Course course;

    @Column(name = "place_number")
    private Integer placeNumber;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "deleted")
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY)
    private List<Study> studies;

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY)
    private List<Lesson> lessons;


}