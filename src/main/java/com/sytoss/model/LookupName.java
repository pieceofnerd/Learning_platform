package com.sytoss.model;

import javax.persistence.*;

@Entity
@Table(name = "lookup_name")
public class LookupName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public LookupName() {
    }
}
