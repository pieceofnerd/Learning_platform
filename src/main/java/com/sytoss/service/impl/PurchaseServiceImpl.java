package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchPurchaseException;
import com.sytoss.model.PriceType;
import com.sytoss.model.PurchaseStatus;
import com.sytoss.model.StudentStatus;
import com.sytoss.model.course.Price;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.PriceRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.PurchaseRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.PurchaseService;
import com.sytoss.service.StudentService;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.web.dto.filter.FilterPurchaseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    private final UserAccountRepository userAccountRepository;

    private final StudyGroupRepository studyGroupRepository;

    private final StudentService studentService;

    private final StudyGroupService studyGroupService;

    private final StudyService studyService;

    private final LookupRepository lookupRepository;

    private final PriceRepository priceRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, UserAccountRepository userAccountRepository,
                               StudyGroupRepository studyGroupRepository, StudentService studentService, StudyGroupService studyGroupService, StudyService studyService, LookupRepository lookupRepository,
                               PriceRepository priceRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userAccountRepository = userAccountRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.studentService = studentService;
        this.studyGroupService = studyGroupService;
        this.studyService = studyService;
        this.lookupRepository = lookupRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public Purchase payCourse(Student student, StudyGroup studyGroup) throws Exception {
        if (userAccountRepository.findOne(student.getId()) == null ||
                studyGroupRepository.findOne(studyGroup.getId()) == null) {
            logger.error("Student and Study group must not be null");
            throw new NullPointerException();
        }

        Purchase purchase = createPurchase(student, studyGroup);

        Purchase createdPurchase = purchaseRepository.save(purchase);

        //adds a student to the group and created study of the student who paid for the course.
        studentService.joinStudyGroup(student,studyGroup);

        logger.info("Course {} was payed by Student with id: {}", purchase.getStudyGroup().getCourse().getName(), purchase.getStudent().getId());
        return createdPurchase;
    }

    private Purchase createPurchase(Student student, StudyGroup studyGroup) {
        Purchase purchase = new Purchase(student, studyGroup);
        purchase.setPurchaseStatus(lookupRepository.findOne(PurchaseStatus.PAYED.getValue()));
        Price price;
        if (Objects.equals(student.getStudentStatus().getId(), StudentStatus.NEWBIE.getValue())) {
            price = priceRepository
                    .findByCourseAndPriceType(studyGroup.getCourse(), lookupRepository.findOne(PriceType.PREMIUM.getValue()));
        } else {
            price = priceRepository
                    .findByCourseAndPriceType(studyGroup.getCourse(), lookupRepository.findOne(PriceType.REGULAR.getValue()));
        }
        purchase.setCost(price.getCost());
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) throws NoSuchPurchaseException { //TODO
        if (!purchaseRepository.exists(purchase.getId())) {
            logger.error("Couldn't find purchase with id {}", purchase.getId());
            throw new NoSuchPurchaseException();
        }
        purchaseRepository.save(purchase);
        logger.info("Purchase with id: {} was updated", purchase.getId());
    }

    @Override
    public List<Purchase> findPurchaseByFilter(UserAccount student, FilterPurchaseDTO filter) { //TODO
        return null;
    }

    @Override
    public boolean refundMoney(Purchase purchase) throws Exception {
        purchase.setPurchaseStatus(lookupRepository.findOne(10L));
        purchase.setUpdatedDate(new Date());

        //upon refund, the student leaves the group
        studentService.leaveStudyGroup(purchase.getStudent(), purchase.getStudyGroup());
        return true;
    }
}