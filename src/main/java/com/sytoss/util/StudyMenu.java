package com.sytoss.util;

import com.sytoss.controller.StudyController;
import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.mapper.StudyMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class StudyMenu {


    private final StudyController studyController;
    private final UserAccountRepository userAccountRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final StudyRepository studyRepository;
    private final UserAccountMapper userAccountMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final StudyMapper studyMapper;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Save study",
                "2. Delete study",
                "3. Update student assessment",
                "4. Update student progress",
                "5. Find studies by filter"
        );
        long studentId = 0L;
        long studyGroupId = 0L;
        UserAccountDTO studentDTO;
        StudyGroupDTO studyGroupDTO;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                studentId = scanInt("Write student id - ");
                studentDTO = userAccountMapper.toDTO(userAccountRepository.findOne(studentId));
                studyGroupId = scanInt("Write study group id - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));
                studyController.saveStudy(studentDTO, studyGroupDTO);
                break;
            case 2:
                long studyId = scanInt("Write study id to delete - ");
                final StudyDTO studyDTO = studyMapper.toDTO(studyRepository.findOne(studyId));
                studyController.deleteStudy(studyDTO);
                break;
            case 3:
                studentId = scanInt("Write student id to update his assessment- ");
                studentDTO = userAccountMapper.toDTO(userAccountRepository.findOne(studentId));
                studyGroupId = scanInt("Write his study group id - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));
                studyController.updateAssessment(studentDTO, studyGroupDTO);
                break;
            case 4:
                studentId = scanInt("Write student id to update his study progress - ");
                studentDTO = userAccountMapper.toDTO(userAccountRepository.findOne(studentId));
                studyGroupId = scanInt("Write his study group id - ");
                studyGroupDTO = studyGroupMapper.toDTO(studyGroupRepository.findOne(studyGroupId));
                studyController.updateProgress(studentDTO, studyGroupDTO);
                break;
            case 5:
                printMenu(
                        "1. Filter by student",
                        "2. Filter by study group"
                );
                FilterStudyDTO filter = selectFilter(scanInt("Select filter - "));
                for (StudyDTO s : studyController.findStudiesByFilter(filter)) {
                    printClassName(s.getClass().getSimpleName());
                    printField("id",s.getId());
                    printField("student id",s.getStudent().getId());
                    printField("study group id",s.getStudyGroup().getId());
                    printField("progress",s.getProgress());
                    printField("assessment",s.getAssessment());
                    printField("certificate",s.getCertificate());
                }
                break;
        }
    }

    private FilterStudyDTO selectFilter(int i) throws Exception {
        FilterStudyDTO filter = new FilterStudyDTO();
        switch (i) {
            case 1:

                filter.setFilter(Filter.STUDENT);
                long studentId = scanInt("Write student id to filter - ");
                filter.setStudent(studentId);
                break;
            case 2:
                filter.setFilter(Filter.STUDY_GROUP);
                long studyGroupId = scanInt("Write study group id to filter - ");
                filter.setStudyGroup(studyGroupId);
                break;
            default:
                throw new Exception("FILTER ERROR");
        }
        return filter;
    }

//    private void printAllPossibleCharacteristics() {
//        long categoryId = scanInt("Write category id or 0 to show all catalogs: ");
//        if (categoryId == 0) {
//            printAllCategoriesWithCharacteristics();
//            categoryId = scanInt("Write catalog id: ");
//        }
//        printPerCategory(categoryId);
//    }

//    private void printAllCategoriesWithCharacteristics() {
//        List<Category> categories = productApi.findAllCategories();
//        for (Category category : categories) {
//            printPerCategory(category.getId());
//        }
//    }
//
//    private void printPerCategory(Long categoryId) {
//        Map<String, List<String>> characteristics = new HashMap<>();
//        List<Characteristic> characteristicsList = productApi.findCharacteristicsPerCategory(categoryId);
//        for (Characteristic characteristic : characteristicsList) {
//            if (!characteristics.containsKey(characteristic.getName()))
//                characteristics.put(characteristic.getName(), new ArrayList<String>());
//            if (!characteristics.get(characteristic.getName()).contains(characteristic.getValue())) {
//                characteristics.get(characteristic.getName()).add(characteristic.getValue());
//            }
//
//        }
//        System.out.println(categoryId + ". " + productApi.findCategoryById(categoryId).getName());
//        for (String key : characteristics.keySet()) {
//            printClassName("Characteristic: " + key);
//            for (String value : characteristics.get(key)) {
//                System.out.println(value);
//            }
//        }
//    }
//
//    private void printProducts() {
//        long productId = scanInt("Write product id: ");
//        printProduct(productId);
//    }
//
//    private void printProduct(Long productId) {
//        ProductCard productCard = productApi.findProductById(productId);
//        printClassName("Product");
//        printField("id", productCard.getId().toString());
//        printField("status", productCard.getStatus());
//        printField("short description", productCard.getShortDescription());
//        printField("full description", productCard.getFullDescription());
//        for (Characteristic characteristic : productCard.getCharacteristics())
//            if (characteristic.getAmount() > 1)
//                printField(characteristic.getName(),
//                        characteristic.getAmount() + "x" + characteristic.getValue());
//            else
//                printField(characteristic.getName(), characteristic.getValue());
//        printField("category", productCard.getProductTemplate().getCategory().getName());
//    }
//
//    private void printCommentaries() {
//        // I don't think that in test you will use BIG number;
//        long productId = scanInt("Write product id or 0 to show all products: ");
//        if (productId == 0) {
//            printAllProductsWithCountOfCommentaries();
//            productId = scanInt("Write product id: ");
//        }
//        printCommentariesPerProduct(productId);
//    }
//
//    private void printAllProductsWithCountOfCommentaries() {
//        List<ProductCard> productCards = productApi.findAllProductCards();
//        for (ProductCard productCard : productCards) {
//            System.out.println(colors.ANSI_BLUE +
//                    productCard.getId() + ". " + productCard.getName() +
//                    colors.ANSI_GREEN +
//                    " has " + commentaryApi.countCommentariesForProductCard(productCard) + " commentaries" +
//                    colors.ANSI_RESET
//            );
//        }
//    }
//
//    private void printCommentariesPerProduct(Long productId) {
//        ProductCard productCard = productApi.findProductById(productId);
//        List<Review> reviews = new ArrayList<>(commentaryApi.findReviewsForProductCard(productCard));
//        List<Question> questions = new ArrayList<>(commentaryApi.findQuestionsForProductCard(productCard));
//
//        System.out.println(MessageFormat.format(
//                "{0} has {1} commentaries.",
//                productCard.getName(),
//                commentaryApi.countCommentariesForProductCard(productCard)
//        ));
//        System.out.println(reviews.size() + " reviews");
//        printReviews(reviews);
//        System.out.println(questions.size() + " questions");
//        printQuestions(questions);
//    }
//
//    private void printReviews(List<Review> reviews) {
//        for (Review review : reviews) {
//            printClassName("Review");
//            printField("id", String.valueOf(review.getId()));
//            printField("author", review.getAuthor().getName());
//            printField("content", review.getContent());
//            printField("rating", String.valueOf(review.getRating()));
//            List<Answer> answers = commentaryApi.findAnswersByRootCommentary(review.getId());
//            if (answers.size() > 0) {
//                printField("answers", String.valueOf(answers.size()));
//                printAnswers(answers);
//            }
//        }
//    }
//
//    private void printQuestions(List<Question> questions) {
//        for (Question question : questions) {
//            printClassName("Question");
//            printField("id", String.valueOf(question.getId()));
//            printField("author", question.getAuthor().getName());
//            printField("content", question.getContent());
//            List<Answer> answers = commentaryApi.findAnswersByRootCommentary(question.getId());
//            if (answers.size() > 0) {
//                printField("answers", String.valueOf(answers.size()));
//                printAnswers(answers);
//            }
//        }
//    }
//
//    private void printAnswers(List<Answer> answers) {
//        for (Answer answer : answers) {
//            printClassName("Answer");
//            printField("id", String.valueOf(answer.getId()));
//            printField("author", answer.getAuthor().getName());
//            printField("reply to",
//                    answer.getRootCommentary().getId() + ". " + answer.getRootCommentary().getAuthor().getName());
//            printField("content", answer.getContent());
//            List<Answer> subAnswers = commentaryApi.findAnswersByRootCommentary(answer.getId());
//            if (subAnswers.size() > 0) {
//                printAnswers(subAnswers);
//            }
//        }
//    }

}