package com.sytoss.service.impl;

import com.sytoss.exception.no_contet_exception.CourseNoContentException;
import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.StudyGroupService;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {

    private static final Logger logger = LoggerFactory.getLogger(StudyGroupServiceImpl.class);

    private final StudyGroupRepository studyGroupRepository;

    private final CourseRepository courseRepository;

    private final StudyRepository studyRepository;

    @Override
    public void createStudyGroup(StudyGroup studyGroup) throws StudyGroupNoContentException {
        if (studyGroup == null) {
            logger.error("Study group cannot be null");
            throw new StudyGroupNoContentException("Study group is null");
        }
        studyGroupRepository.save(studyGroup);
    }

    @Override
    public void updateStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException, StudyGroupNoContentException {
        if (studyGroup == null) {
            logger.error("Study group cannot be null");
            throw new StudyGroupNoContentException("Study group is null");
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
    public void updateFreePlaceNumber(StudyGroup studyGroup) throws StudyGroupNoContentException {
        if (studyGroup == null)
            throw new StudyGroupNoContentException("StudyGroup is null");

        studyGroup.setFreePlaceNumber(freePlaceNumberCalc(studyGroup));
        studyGroup.setUpdatedDate(new Date());

       studyGroupRepository.save(studyGroup);
    }

    @Override
    public List<StudyGroup> findStudyGroupsByCourse(Course course) throws CourseNoContentException {
        if (course == null) {
            logger.error("Course must not be null");
            throw new CourseNoContentException("Course is null");
        }
        return studyGroupRepository.findStudyGroupsByCourseAndDeletedIsFalse(course);
    }

    @Override
    public List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter) {
        switch (filter.getFilter()) {
            case COURSE: {
                return studyGroupRepository.findStudyGroupsByCourseAndDeletedIsFalse(courseRepository.findOne(filter.getCourse()));
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
            throw new NoSuchStudyGroupException("No such study group exists");
        }
    }

    private int freePlaceNumberCalc(StudyGroup studyGroup) {
        List<Study> studies = studyRepository.findStudiesByDeletedIsFalseAndStudyGroup(studyGroup);
        int freePlaceNumber = studyGroup.getPlaceNumber() - studies.size();
        return Math.max(freePlaceNumber, 0);
    }
}