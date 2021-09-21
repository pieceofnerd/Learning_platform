package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationDTO {
    private Long id;
    private Long sender;
    private Long receiver;
    private Date sendDate;
    private String content;
    private Long lesson;
    private Long homework;
    private Integer score;
    private Boolean active;
    private Date updateDate;
}