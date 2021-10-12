package com.sytoss.model.course;


import com.sytoss.model.Lookup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "price")
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne()
    private Course course;

    @JoinColumn(name = "price_type")
    @ManyToOne(optional = false)
    private Lookup priceType;


    @ManyToOne()
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "cost")
    private BigDecimal cost;

}
