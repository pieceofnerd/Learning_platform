package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;

    private UserAccountDTO student;

    private StudyGroupDTO studyGroup;

    private LookupDTO purchaseStatus;

    private BigDecimal cost;

    private Date purchaseDate;

    private Date updatedDate;


}
