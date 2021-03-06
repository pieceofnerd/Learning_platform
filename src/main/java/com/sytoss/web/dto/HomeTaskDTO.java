package com.sytoss.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeTaskDTO {
    private Long id;
    private String task;
    private MediaDTO filePath;
    private Date deadlineDate;
}