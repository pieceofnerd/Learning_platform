package com.sytoss.service.impl;

import com.sytoss.exception.CourseNotPaidException;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.NoSuchCourseException;
import com.sytoss.exception.no_such_exception.NoSuchStudyException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.CourseRating;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.model.enums.PurchaseStatus;
import com.sytoss.repository.course.CourseRatingRepository;
import com.sytoss.repository.education.PurchaseRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudyService studyService;
    private final StudyGroupService studyGroupService;
    private final PurchaseService purchaseService;
    private final CourseService courseService;
    private final UserAccountRepository userAccountRepository;
    private final StudyRepository studyRepository;
    private final PurchaseRepository purchaseRepository;
    private final CourseRatingRepository courseRatingRepository;

    @Autowired
    public StudentServiceImpl(UserAccountRepository userAccountRepository, StudyRepository studyRepository, StudyService studyService, StudyGroupService studyGroupService, PurchaseService purchaseService, PurchaseRepository purchaseRepository, CourseRatingRepository courseRatingRepository, CourseService courseService) {
        this.userAccountRepository = userAccountRepository;
        this.studyRepository = studyRepository;
        this.studyService = studyService;
        this.studyGroupService = studyGroupService;
        this.purchaseService = purchaseService;
        this.purchaseRepository = purchaseRepository;
        this.courseRatingRepository = courseRatingRepository;
        this.courseService = courseService;
    }


    @Override
    public List<Study> findStudiesByStudent(Student student) throws NoSuchUserAccountException, UserAccountNoContentException {
        if (student == null)
            throw new UserAccountNoContentException("User account is null");

        if (!userAccountRepository.exists(student.getId()))
            throw new NoSuchUserAccountException("User account not exist");

        return studyRepository.findStudiesByDeletedIsFalseAndStudent(student);
    }

    @Override
    public void rateCourse(Course course, Integer rateValue) throws NoSuchCourseException, CourseNoContentException {
        CourseRating courseRating = new CourseRating();
        courseRating.setRating(rateValue);
        courseRating.setCourse(course);
        courseRatingRepository.save(courseRating);
        courseService.updateCourseRating(course);
    }

    @Override
    public List<Purchase> findPurchaseByStudent(UserAccount student) {
        return purchaseService.findPurchasesByStudent((Student) student);
    }

    @Override
    public Purchase payCourse(Student student, StudyGroup studyGroup) throws CourseNotPaidException, UserAccountNoContentException, PurchaseNoContentException, StudyGroupNoContentException, NoSuchStudyGroupException, NoSuchUserAccountException {
        Purchase purchase = purchaseService.payCourse(student, studyGroup);
        joinStudyGroup(student, studyGroup);
        return purchase;
    }

    @Override
    public void returnCourse(UserAccount student, StudyGroup studyGroup) throws StudyGroupNoContentException, NoSuchStudyException, StudyNoContentException {
        Purchase purchase = purchaseRepository.findByStudentAndStudyGroup(student, studyGroup);

        purchaseService.refundMoney(purchase);
        leaveStudyGroup(student, studyGroup);
    }

    @Override
    public void joinStudyGroup(UserAccount student, StudyGroup studyGroup) throws PurchaseNoContentException, CourseNotPaidException, UserAccountNoContentException, StudyGroupNoContentException {
        Purchase purchase = purchaseRepository.findByStudentAndStudyGroup(student, studyGroup);

        if (purchase == null)
            throw new PurchaseNoContentException("Purchase is null");

        if (!purchase.getPurchaseStatus().getId().equals(PurchaseStatus.PAYED.getValue()))
            throw new CourseNotPaidException("Student did not pay for the course");

        studyService.saveStudy(student, studyGroup);

        studyGroupService.updateFreePlaceNumber(studyGroup);
    }

    @Override
    public void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) throws NoSuchStudyException, StudyNoContentException, StudyGroupNoContentException {

        //when a student leaves the group, all studies of the student in this group are deleted.
        Study study = studyRepository.findStudyByDeletedIsFalseAndStudentAndStudyGroup(student, studyGroup);
        if (study != null) {
            studyService.deleteStudy(study);
            studyGroupService.updateFreePlaceNumber(studyGroup);
        }
    }
}