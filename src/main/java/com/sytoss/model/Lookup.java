package com.sytoss.model;

import javax.persistence.*;

@Entity
@Table(name = "lookup")
public class Lookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "lookup_name_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LookupName lookupName;
    
    @Column
    private String value;

    public Lookup() {
    }

    public LookupName getLookupName() {
        return lookupName;
    }

    public void setLookupName(LookupName lookupName) {
        this.lookupName = lookupName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
