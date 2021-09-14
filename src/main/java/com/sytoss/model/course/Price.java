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

    @JoinColumn(name = "promotion_id", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup promotion;

    @Column
    private Double cost;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lookup getPriceType() {
        return priceType;
    }

    public void setPriceType(Lookup priceType) {
        this.priceType = priceType;
    }

    public Lookup getPromotion() {
        return promotion;
    }

    public void setPromotion(Lookup promotion) {
        this.promotion = promotion;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
