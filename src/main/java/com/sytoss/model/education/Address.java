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
}
