package com.sytoss.service.impl;

import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_contet_exception.UserAccountNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPurchaseException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.course.Price;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.user.Student;
import com.sytoss.model.enums.PriceType;
import com.sytoss.model.enums.PurchaseStatus;
import com.sytoss.model.enums.StudentStatus;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.PriceRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.PurchaseRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.PurchaseService;
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

    private final LookupRepository lookupRepository;

    private final PriceRepository priceRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, UserAccountRepository userAccountRepository,
                               StudyGroupRepository studyGroupRepository, LookupRepository lookupRepository,
                               PriceRepository priceRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userAccountRepository = userAccountRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.lookupRepository = lookupRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public Purchase payCourse(Student student, StudyGroup studyGroup) throws UserAccountNoContentException, StudyGroupNoContentException, NoSuchStudyGroupException, NoSuchUserAccountException {
        if (student == null) {
            throw new UserAccountNoContentException("User account is null");
        }
        if (studyGroup == null) {
            throw new StudyGroupNoContentException("Study group is null");
        }
        if (!userAccountRepository.exists(student.getId())) {
            throw new NoSuchUserAccountException("No such user account exists");
        }
        if (!studyGroupRepository.exists(studyGroup.getId())) {
            throw new NoSuchStudyGroupException("No such study group exists");
        }

        Purchase purchase = createPurchase(student, studyGroup);

        Purchase createdPurchase = purchaseRepository.save(purchase);


        logger.info("Course {} was payed by Student with id: {}", purchase.getStudyGroup().getCourse().getName(), purchase.getStudent().getId());
        return createdPurchase;
    }

    private Purchase createPurchase(Student student, StudyGroup studyGroup) {
        Purchase purchase = new Purchase();
        purchase.setStudent(student);
        purchase.setStudyGroup(studyGroup);
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
        //default status eq PAYED
        purchase.setPurchaseStatus(lookupRepository.findOne(8L));
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) throws NoSuchPurchaseException { //TODO
        if (!purchaseRepository.exists(purchase.getId())) {
            logger.error("Couldn't find purchase with id {}", purchase.getId());
            throw new NoSuchPurchaseException("No such purchase exists");
        }
        purchaseRepository.save(purchase);
        logger.info("Purchase with id: {} was updated", purchase.getId());
    }

    @Override
    public List<Purchase> findPurchasesByStudent(Student student) {
        return purchaseRepository.findPurchasesByStudent(student);
    }

    @Override
    public List<Purchase> findPurchaseByFilter(FilterPurchaseDTO filter) { //TODO
        return null;
    }

    @Override
    public boolean refundMoney(Purchase purchase) {
        purchase.setPurchaseStatus(lookupRepository.findOne(10L));
        purchase.setUpdatedDate(new Date());

        return true;
    }
}