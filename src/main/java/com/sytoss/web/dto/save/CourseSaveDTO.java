package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CourseSaveDTO {

    private String name;

    private String recommendedLiterature;

    private String description;

    private CategorySaveDTO category;

    private MediaSaveDTO certificateTemplate;

    private MediaSaveDTO coursePhoto;

    private List<TopicSaveDTO> topics;

    private List<PriceSaveDTO> prices;

}
