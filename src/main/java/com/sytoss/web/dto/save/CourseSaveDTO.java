package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSaveDTO {

    private String name;

    private String recommendedLiterature;

    private String description;

    private CategoryDTO category;

    private MediaSaveDTO certificateTemplate;

    private MediaSaveDTO coursePhoto;

    private List<TopicSaveDTO> topics;

    private List<PriceSaveDTO> prices;

}
