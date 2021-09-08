package com.sytoss.model.course;


import com.sytoss.model.Lookup;

import javax.persistence.*;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "price_type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup priceType;

    @JoinColumn(name = "promotion_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup promotion;

    @Column
    private Double cost;

    public Price() {
    }
}
