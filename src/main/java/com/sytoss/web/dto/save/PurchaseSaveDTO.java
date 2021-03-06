package com.sytoss.web.dto.save;

import com.sytoss.web.dto.LookupDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseSaveDTO {

    private UserAccountDTO student;

    private StudyGroupDTO studyGroup;

    private LookupDTO purchaseStatus;

    private BigDecimal cost;

}