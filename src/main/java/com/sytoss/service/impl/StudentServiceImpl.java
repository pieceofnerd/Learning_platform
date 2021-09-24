package com.sytoss.service.impl;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.StudentService;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudyRepository studyRepository;
    private final StudyService studyService;
    private final StudyGroupService studyGroupService;

    @Override
    public void rateCourse(UserAccount student, Course course, Integer rateValue) {

    }

    @Override
    public List<Course> findCoursesByStudentByFilter(UserAccount student, FilterUserAccountDTO filter) {
        return null;
    }

    @Override
    public List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup) {
        return null;
    }

    @Override
    public Purchase payCourse(UserAccount student, Course course) {
        return null;
    }

    @Override
    public void returnCourse(UserAccount student, Course course) {
        //TODO
    }

    @Override
    public void joinStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception {
        studyService.saveStudy(student,studyGroup);
    }

    @Override
    public void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception {

        //when a student leaves the group, all studies of the student in this group are deleted.
        Study study = studyRepository.findStudyByStudentAndStudyGroup(student, studyGroup);
        if (study != null) {
            studyService.deleteStudy(study);
        }
    }
}