package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CourseDTO {

    private String name;

    private String recommendedLiterature;

    private String description;

    private CategoryDTO category;

    private MediaDTO certificateTemplate;

    private MediaDTO photo;

    private List<TopicDTO> topics;

    private List<PriceDTO> prices;

}
