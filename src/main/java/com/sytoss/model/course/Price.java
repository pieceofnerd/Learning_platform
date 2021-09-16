package com.sytoss.model.course;


import com.sytoss.model.Lookup;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
//@Getter
//@Setter

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false)
    private Course course;

    @JoinColumn(name = "price_type")
    @ManyToOne(optional = false)
    private Lookup priceType;

    @JoinColumn(name = "promotion_id")
    @ManyToOne
    private Promotion promotion;

    @Column(name = "cost")
    private BigDecimal cost;


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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
