package com.sytoss.web.dto.filter;

import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterStudyDTO {

    private Long id;

    private Long student;

    private Long studyGroup;

    private Filter filter;

}