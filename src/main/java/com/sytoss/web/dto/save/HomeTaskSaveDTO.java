package com.sytoss.web.dto.save;

import com.sytoss.web.dto.MediaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeTaskSaveDTO {

    private String task;

    private MediaDTO filePath;

    private Date deadlineDate;
}