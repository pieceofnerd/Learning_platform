package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class PurchaseDTO {
//    private StudentDTO student;

    private CourseDTO course;

    private String purchaseStatus;

    private Double cost;

    private Date purchaseDate;
}