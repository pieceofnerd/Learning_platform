package com.sytoss.web.dto.save;

import com.sytoss.web.dto.MediaDTO;
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

    private MediaDTO certificateTemplate;

    private MediaDTO coursePhoto;

    private List<TopicSaveDTO> topics;

    private List<PriceSaveDTO> prices;

}
