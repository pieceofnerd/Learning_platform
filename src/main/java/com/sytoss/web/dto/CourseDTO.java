package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private List<TopicDTO> topics;

    private List<PriceDTO> prices;

}
