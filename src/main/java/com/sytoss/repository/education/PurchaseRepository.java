package com.sytoss.repository.education;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Purchase findByStudentAndStudyGroup(UserAccount student, StudyGroup studyGroup);

    List<Purchase> findPurchasesByStudent(UserAccount student);
}
