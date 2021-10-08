package com.sytoss.model.course;

import com.sytoss.model.Lookup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "percent")
    private Integer percent;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm aa")
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm aa")
    private Date endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "promotion_state_id")
    private Lookup promotionState;

    @OneToMany(
            mappedBy = "promotion",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE}
    )
    private List<Price> prices;

}
