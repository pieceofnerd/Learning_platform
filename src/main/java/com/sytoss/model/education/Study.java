package com.sytoss.model.education;


import com.sytoss.model.Media;
import com.sytoss.model.course.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "study")
public class Study {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private UserAccount student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @Column(name = "progress")
    private Double progress = 0.0d;

    @Column(name = "assessment")
    private Double assessment;

    @ManyToOne
    @Column(name = "certificate_id")
    private Media certificate;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

}