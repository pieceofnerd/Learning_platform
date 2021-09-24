package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchStudyException;
import com.sytoss.model.HomeworkStatus;
import com.sytoss.model.Lookup;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {

    private final static Logger logger = LoggerFactory.getLogger(StudyServiceImpl.class);

    private final StudyRepository studyRepository;

    private final UserAccountRepository userAccountRepository;

    private final StudyGroupRepository studyGroupRepository;

    private final StudyGroupService studyGroupService;

    private final HomeworkRepository homeworkRepository;

    private final LookupRepository lookupRepository;


    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository, UserAccountRepository userAccountRepository,
                            StudyGroupRepository studyGroupRepository, StudyGroupService studyGroupService, HomeworkRepository homeworkRepository,
                            LookupRepository lookupRepository) {
        this.studyRepository = studyRepository;
        this.userAccountRepository = userAccountRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.studyGroupService = studyGroupService;
        this.homeworkRepository = homeworkRepository;
        this.lookupRepository = lookupRepository;
    }


    @Override
    public void saveStudy(UserAccount student, StudyGroup studyGroup) throws Exception {
        if (student == null || studyGroup == null) {
            logger.error("Student and study group must not be null");
            return;
        }

        Study study = new Study();
        study.setStudent(student);
        study.setStudyGroup(studyGroup);


        studyRepository.save(study);

        studyGroupService.updateFreePlaceNumber(studyGroup);
    }

    @Override
    public void deleteStudy(Study study) throws NoSuchStudyException {
        if (study == null) {
            logger.error("Study must not be null");
            return;
        }

        if (!studyRepository.exists(study.getId())) {
            logger.error("Couldn't find study with id: {}", study.getId());
            throw new NoSuchStudyException();
        }

        study.setDeleted(true);
        studyRepository.save(study);
    }

    @Override
    public void updateProgress(UserAccount student, StudyGroup studyGroup) throws Exception {
        if (student == null || studyGroup == null) {
            logger.error("Student and study group must not be null");
            return;
        }

        Study study = checkStudyExistence(student, studyGroup);
        Lookup fulfilmentHomework = lookupRepository.findOne(HomeworkStatus.PROVEN.getValue());
        List<Homework> homeworks = new ArrayList<>();
        List<Lesson> lessons = study.getStudyGroup().getLessons();
        for (Lesson lesson : lessons) {
            Homework homework = homeworkRepository.findProvenHomeworkByAuthorAndHomeTask(student, lesson.getHomeTask(),fulfilmentHomework);
            if(homework!=null)
            homeworks.add(homework);
        }

        study.setProgress(progressPercentCalc(lessons.size(),homeworks.size()));
    }

    @Override
    public void updateAssessment(UserAccount student, StudyGroup studyGroup) throws Exception {
        if (student == null || studyGroup == null) {
            logger.error("Student and study group must not be null");
            return;
        }

        Study study = checkStudyExistence(student, studyGroup);

        double generalScore = 0.0;
        Lookup fulfilmentHomework = lookupRepository.findOne(HomeworkStatus.PROVEN.getValue());
        List<Lesson> lessons = study.getStudyGroup().getLessons();
        List<Homework> homeworks = new ArrayList<>();
        for (Lesson lesson : lessons) {
            Homework homework = homeworkRepository.findHomeworkByAuthorAndHomeTask(student, lesson.getHomeTask(), fulfilmentHomework);
            if(homework!=null) {
                generalScore += homework.getFeedback().getScore();
                homeworks.add(homework);
            }
        }
        study.setAssessment(generalScore / homeworks.size());
    }

    @Override
    public Study findStudyById(Long id) throws Exception {
        if (!studyRepository.exists(id))
            throw new Exception("Study with id = " + id + " not found!");
        return studyRepository.findOne(id);
    }

    @Override
    public List<Study> findStudiesByFilter(FilterStudyDTO filter) throws Exception {
        List<Study> studies = new ArrayList<>();
        switch (filter.getFilter()) {
            case STUDENT:
                studies.addAll(studyRepository.findStudiesByStudent(userAccountRepository.findOne(filter.getStudent())));
                break;
            case STUDY_GROUP:
                studies.addAll(studyRepository.findStudiesByStudyGroup(studyGroupRepository.findOne(filter.getStudyGroup())));
                break;
            default:
                throw new Exception("FILTER ERROR");
        }
        return studies;
    }

    private Study checkStudyExistence(UserAccount student, StudyGroup studyGroup) throws NoSuchStudyException {
        Study study = studyRepository.findStudyByStudentAndStudyGroup(student, studyGroup);

        if (study == null) {
            logger.error("Couldn't find study of {} student from {} study group", student.getId(), studyGroup.getId());
            throw new NoSuchStudyException();
        }
        return study;
    }

    private double progressPercentCalc(double total, double done) {
        return (done / total) * 100;
    }
}