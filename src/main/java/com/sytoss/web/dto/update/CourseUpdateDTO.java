package com.sytoss.web.dto.update;

import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.MediaDTO;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.TopicDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDTO {

    private Long id;

    private String name;

    private String recommendedLiterature;

    private Boolean active;

    private MediaSaveDTO coursePhoto;

    private CategoryDTO category;

    private Double rating;

    private String description;

    private MediaDTO certificateTemplate;

    private List<TopicDTO> topics;

    private List<PriceDTO> prices;

}
