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

    private Long categoryId;

    private MediaSaveDTO certificateTemplate;

    private MediaSaveDTO coursePhoto;

    private List<TopicSaveDTO> topics;

    private List<PriceSaveDTO> prices;

}
