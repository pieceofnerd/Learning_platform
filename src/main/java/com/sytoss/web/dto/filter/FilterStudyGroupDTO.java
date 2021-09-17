package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterStudyGroupDTO {
    private Long course;
    private boolean deleted;
    private Filter filter;
}