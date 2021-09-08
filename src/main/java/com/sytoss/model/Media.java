package com.sytoss.model;

import javax.persistence.*;

@Entity
@Table
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "media_path")
    private String mediaPath;

    public Media() {
    }
}
