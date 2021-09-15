package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    private String name;

    private  String recommendedLiterature;

    private Boolean active;

    private MediaDTO coursePhoto;

    private CategoryDTO categoryDTO;

    private Double rating;

    private String description;

    private MediaDTO certificateTemplate;

    private List<TopicDTO> topicDTO;

    private List<PriceDTO> priceDTO;

}
