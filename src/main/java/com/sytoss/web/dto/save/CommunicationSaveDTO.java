package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationSaveDTO {
    private Long sender;
    private Long receiver;
    private String content;
    private Long lesson;
    private Long homework;
    private Integer score;
    private Boolean active;
}