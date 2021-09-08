package com.sytoss.model.course;

import com.sytoss.model.Lookup;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @Column
    @Min(0)
    @Max(100)
    private Integer percent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @JoinColumn(name = "promotion_state_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup promotionState;

    public Promotion() {
    }
}
