package com.sytoss.model.course;

import com.sytoss.model.Lookup;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "percent")
    private Integer percent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @JoinColumn(name = "promotion_state_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup promotionState;

    @OneToMany(mappedBy = "promotion",fetch = FetchType.LAZY)
    private List<Price> prices;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Lookup getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(Lookup promotionState) {
        this.promotionState = promotionState;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
