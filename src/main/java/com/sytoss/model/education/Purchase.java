package com.sytoss.model.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private UserAccount student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name = "purchase_status_id")
    private Lookup purchaseStatus;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "purchase_date")
    private Date purchaseDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();
}