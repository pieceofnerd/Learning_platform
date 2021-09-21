package com.sytoss.service.impl;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.communication.CommunicationRepository;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.TopicRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserAccountService userAccountService;
    private final StudyGroupService studyGroupService;
    private final HomeworkRepository homeworkRepository;
    private final CommunicationRepository communicationRepository;
    private final TopicRepository topicRepository;
    private final LessonRepository lessonRepository;


    @Override
    public boolean saveStudy(UserAccount student, StudyGroup studyGroup) {
        if (student == null)
            return false;
        if (studyGroup == null)
            return false;

        Study study = new Study();
        study.setStudent(student);
        study.setStudyGroup(studyGroup);
        studyRepository.save(study);
        return true;
    }

    @Override
    public boolean deleteStudy(Study study) {
        if (study == null)
            return false;
        if (!studyRepository.exists(study.getId()))
            return false;
        study.setDeleted(true);
        studyRepository.save(study);
        return true;
    }

    @Override
    public void updateProgress(UserAccount student, StudyGroup studyGroup) throws Exception {
        if (student == null)
            throw new Exception("Student is null");
        if (studyGroup == null)
            throw new Exception("Studygroup is null");

        Study study = studyRepository.findStudyByStudentAndStudyGroup(student, studyGroup);

        if (study == null)
            throw new Exception("Study is null");


        double numberHomeworks = 0;
        final List<Lesson> lessons = lessonRepository.findLessonsByActiveIsTrueAndStudyGroup(studyGroup);
        double totalNumberLessons = lessons.size();

        for (Lesson lesson : lessons) {

            System.out.println("lesson id - " + lesson.getId());

            for (Homework homework : lesson.getHomeTask().getHomeworks()) {

                if (homework.getAuthor().getId().equals(student.getId())) {
                    for (Feedback feedback : homework.getFeedbacks()) {
                        if (feedback.getScore() != null) {

                            System.out.println("FB id - " + feedback.getId());
                            System.out.println("HW id - " + homework.getId());

                            numberHomeworks++;
                        }
                    }
                }
            }
        }
        double progress = progressPercentCalc(totalNumberLessons, numberHomeworks);
        study.setProgress(progress);
        studyRepository.save(study);

        System.out.println("total number of " + studyGroup.getCourse().getName() + " lessons " + totalNumberLessons);
        System.out.println("total number of " + studyGroup.getCourse().getName() + " homeworks " + numberHomeworks);
        System.out.println("progress - " + progress);
    }

    @Override
    public void updateAssessment(UserAccount student, StudyGroup studyGroup) throws Exception {
        if (student == null)
            throw new Exception("Student is null");
        if (studyGroup == null)
            throw new Exception("Studygroup is null");

        Study study = studyRepository.findStudyByStudentAndStudyGroup(student, studyGroup);

        if (study == null)
            throw new Exception("Study is null");

        //    List<Homework> homeworks = new ArrayList<>();
        final Long studyGroupId = studyGroup.getId();
        double result = 0;
        int denominator = 0;

        for (Homework homework : homeworkRepository.findAllByAuthorAndActiveIsTrue(student)) {
            if (homework.getHomeTask().getLesson().getStudyGroup().getId().equals(studyGroupId)) {
           //     result += communicationRepository.findFeedbackByHomework(homework).getScore();

                //       homeworks.add(homework);
                denominator++;
            }
        }

        result /= denominator;
        study.setAssessment(result);
        studyRepository.save(study);
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
            case STUDENT: {
                studies.addAll(studyRepository.findStudiesByStudent(userAccountService.findUserAccountById(filter.getStudent())));
                break;
            }
            case STUDY_GROUP: {
                studies.addAll(studyRepository.findStudiesByStudyGroup(studyGroupService.findStudyGroupById(filter.getStudyGroup())));
                break;
            }
        }
        return studies;
    }

    private double progressPercentCalc(double total, double done) {
        return (done / total) * 100;
    }
}