package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchStudyGroupException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.service.StudyGroupService;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {

    private static final Logger logger = LoggerFactory.getLogger(StudyGroupServiceImpl.class);

    private final StudyGroupRepository studyGroupRepository;

    private final CourseRepository courseRepository;

    @Override
    public void createStudyGroup(StudyGroup studyGroup) {
        if (studyGroup == null) {
            logger.error("Study group cannot be null");
            return;
        }
        studyGroupRepository.save(studyGroup);
    }

    @Override
    public void updateStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException {
        if (studyGroup == null) {
            logger.error("Study group cannot be null");
            return;
        }
        checkExistence(studyGroup);
        studyGroupRepository.save(studyGroup);
    }


    @Override
    public void deleteStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException {
        if (studyGroup == null) {
            logger.error("Study group cannot be null");
            return;
        }
        checkExistence(studyGroup);
        studyGroup.setDeleted(true);
        studyGroupRepository.save(studyGroup);
    }

    @Override
    public List<StudyGroup> findStudyGroupsByCourse(Course course) {
        if (course == null) {
            logger.error("Course must not be null");
            return null;
        }
        return studyGroupRepository.findStudyGroupsByCourse(course);
    }

    @Override
    public List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter) {
        switch (filter.getFilter()) {
            case COURSE: {
                return studyGroupRepository.findStudyGroupsByCourse(courseRepository.findOne(filter.getCourse()));
            }
            case DELETED: {
                return studyGroupRepository.findStudyGroupsByDeleted(filter.isDeleted());
            }
        }
        return null;
    }

    private void checkExistence(StudyGroup studyGroup) throws NoSuchStudyGroupException {
        if (!studyGroupRepository.exists(studyGroup.getId())) {
            logger.error("Couldn't find study group with id: {}", studyGroup.getId());
            throw new NoSuchStudyGroupException();
        }
    }

}