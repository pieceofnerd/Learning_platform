package com.sytoss.service.impl;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.service.StudyGroupService;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final CourseRepository courseRepository;

    @Override
    public boolean createStudyGroup(StudyGroup studyGroup) {
        if (studyGroup == null)
            return false;
        studyGroupRepository.save(studyGroup);
        return true;
    }

    @Override
    public boolean updateStudyGroup(StudyGroup studyGroup) {
        if (studyGroup == null)
            return false;
        if (!studyGroupRepository.exists(studyGroup.getId()))
            return false;
        studyGroupRepository.save(studyGroup);
        return true;
    }

    @Override
    public boolean deleteStudyGroup(StudyGroup studyGroup) {
        if (studyGroup == null)
            return false;
        if (!studyGroupRepository.exists(studyGroup.getId()))
            return false;
        studyGroup.setDeleted(true);
        studyGroupRepository.save(studyGroup);
        return true;
    }

    @Override
    public List<StudyGroup> findStudyGroupsByCourse(Course course) throws Exception {
        if (course == null)
            throw new Exception("No content");
        List<StudyGroup> studyGroups = studyGroupRepository.findStudyGroupsByCourse(course);
        if (studyGroups.isEmpty())
            throw new Exception("No content");
        return studyGroups;
    }

    @Override
    public List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup) {
        studyGroup.getStudies();
        //TODO
        return null;
    }

    @Override
    public List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter) {
        List<StudyGroup> studyGroups = new ArrayList<>();
        switch (filter.getFilter()) {
            case COURSE: {
                studyGroups.addAll(studyGroupRepository.findStudyGroupsByCourse(courseRepository.findOne(filter.getCourse())));
                break;
            }
            case DELETED: {
                studyGroups.addAll(studyGroupRepository.findStudyGroupsByDeleted(filter.isDeleted()));
                break;
            }
        }
        return studyGroups;
    }
}