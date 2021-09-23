package com.sytoss.util;

import com.sytoss.controller.StudyGroupController;
import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import com.sytoss.web.dto.save.StudyGroupSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class StudyGroupMenu {
    private final StudyGroupController studyGroupController;
    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Save study group",
                "2. Update study group",
                "3. Delete study group",
                "4. Find students by study group",
                "5. Find study groups by filter"
        );

        long studyGroupId;
        StudyGroupDTO studyGroupDTO;

        switch (scanInt()) {
            case -1:
                return;
            case 1:
                long courseId = scanInt("Write course id - ");
                int placeNumber = scanInt("Write place number - ");
                int freePlaceNumber = scanInt("Write free place number - ");
                Date startDate = Date.valueOf(scanLine("Write start date - "));
                Date endDate = Date.valueOf(scanLine("Write end date - "));
                StudyGroupSaveDTO studyGroupSaveDTO = new StudyGroupSaveDTO(courseId, placeNumber,freePlaceNumber, startDate, endDate);
                studyGroupController.createStudyGroup(studyGroupSaveDTO);
                break;
            case 2:
                studyGroupId = scanInt("Write study group id to update - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));
                studyGroupController.updateStudyGroup(studyGroupDTO);
                break;
            case 3:
                studyGroupId = scanInt("Write study group id to delete - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));
                studyGroupController.deleteStudyGroup(studyGroupDTO);
                break;
            case 4:
                studyGroupId = scanInt("Write study group id to find students - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));

                for (UserAccountDTO student : studyGroupController.findStudentsByStudyGroup(studyGroupDTO)) {
                    printClassName(student.getClass().getSimpleName());
                    printField("id",student.getId().toString());
                    printField("First name",student.getFirstName());
                    printField("Second name",student.getSecondName());
                    printField("birthday",student.getBirthday());
                    printField("email",student.getEmail());
                    printField("last activity",student.getLastActivity());
                    printField("studies",student.getStudies());
                    printField("homeworks",student.getHomeworks());
                }
                break;
            case 5:
                printMenu(
                        "1. Filter by course",
                        "2. Filter by deleted"
                );
                FilterStudyGroupDTO filter = selectFilter(scanInt("Select filter - "));
                System.out.println(filter.getFilter());
                System.out.println(filter.getCourse());
                System.out.println(filter.isDeleted());
                for (StudyGroupDTO sg : studyGroupController.findStudyGroupsByFilter(filter)) {
                    printClassName(sg.getClass().getSimpleName());
                    printField("id",sg.getId());
                    printField("course",sg.getCourse().getName());
                    printField("place number",sg.getPlaceNumber());
                    printField("start date",sg.getStartDate());
                    printField("end date",sg.getEndDate());
                }
                break;
        }
    }

    private FilterStudyGroupDTO selectFilter(int i) throws Exception {
        FilterStudyGroupDTO filter = new FilterStudyGroupDTO();
        switch (i) {
            case 1:

                filter.setFilter(Filter.COURSE);
                long courseId = scanInt("Write course id to filter - ");
                filter.setCourse(courseId);
                break;
            case 2:
                filter.setFilter(Filter.DELETED);
                long deleted = scanInt("Find (1)deleted study groups or (2)not deleted - ");
                if (deleted == 1)
                    filter.setDeleted(true);
                else if (deleted == 2)
                    filter.setDeleted(false);
                break;
            default:
                throw new Exception("FILTER ERROR");
        }
        return filter;
    }
}