package com.sytoss.model.education;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String country;

    @NotBlank
    @Column
    private  String region;

    @NotBlank
    @Column
    private String locality;

    @NotBlank
    @Column
    private String postcode;

    @NotBlank
    @Column(name = "street_name")
    private String streetName;

    @Min(1)
    @Column(name = "house_number")
    private Integer houseNumber;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
}
