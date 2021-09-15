package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class PurchaseSaveDTO {
//    private StudentDTO student;

//    private CourseSaveDTO course;

    private String purchaseStatus;

    private Double cost;

    private Date purchaseDate;
}