package com.sytoss.controller;

import com.sytoss.mapper.StudyMapper;
import com.sytoss.service.StudyService;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyMapper mapper;

    public List<StudyDTO> findAll() {
       return mapper.toListDTO(studyService.findAll());
    }

    public List<StudyDTO> findStudiesByFilter(FilterStudyDTO filter) throws Exception {
        return mapper.toListDTO(studyService.findStudiesByFilter(filter));
    }
}