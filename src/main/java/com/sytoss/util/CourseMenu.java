package com.sytoss.util;

import com.sytoss.controller.CourseController;
import com.sytoss.mapper.CategoryMapper;
import com.sytoss.mapper.CourseMapper;
import com.sytoss.mapper.LookupMapper;
import com.sytoss.model.course.Course;
import com.sytoss.model.enums.PriceType;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.CategoryRepository;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.*;
import com.sytoss.web.dto.save.*;
import com.sytoss.web.dto.update.CourseUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sytoss.util.MenuUtils.printField;

@Component
@Transactional
public class CourseMenu {

    private final CourseController courseController;

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final CourseService courseService;

    private final LookupRepository lookupRepository;

    private final CategoryMapper categoryMapper;

    private final CourseMapper courseMapper;

    private final LookupMapper lookupMapper;

    public CourseMenu(CourseController courseController, CategoryRepository categoryRepository,
                      CourseRepository courseRepository, CourseService courseService, LookupRepository lookupRepository, CategoryMapper categoryMapper,
                      CourseMapper courseMapper, LookupMapper lookupMapper) {
        this.courseController = courseController;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
        this.lookupRepository = lookupRepository;
        this.categoryMapper = categoryMapper;
        this.courseMapper = courseMapper;
        this.lookupMapper = lookupMapper;
    }

    public void start() {
        menu();
        switch (MenuUtils.scanInt()) {
            case 1: {

                CourseSaveDTO courseDTO = new CourseSaveDTO();
                setCourseName(courseDTO);
                setRecommendedLiterature(courseDTO);
                setCategoryDTO(courseDTO);
                setCourseDescription(courseDTO);
                courseDTO.setCoursePhoto(addMedia("Please add course photo: "));
                courseDTO.setCertificateTemplate(addMedia("Please add certificate template: "));

                List<TopicSaveDTO> topics = new ArrayList<>();
                List<LessonTemplateSaveDTO> lessons = new ArrayList<>();
                boolean addTopic = true;
                while (addTopic) {
                    String topicName = MenuUtils.scanLine("Please, enter a topic name: ");
                    String topicDescription = MenuUtils.scanLine("Please, enter an topic description: ");
                    boolean addLessonTemplate = true;
                    while (addLessonTemplate) {
                        String lessonTemplateName = MenuUtils.scanLine("Please, enter a lesson template name: ");
                        String lessonTemplateDescription = MenuUtils.scanLine("Please, enter an topic description: ");
                        int duration = MenuUtils.scanInt("Please, enter lesson duration in minutes: ");
                        MediaSaveDTO mediaSaveDTO = addMedia("Please, enter a path to content: ");
                        lessons.add(new LessonTemplateSaveDTO(lessonTemplateName, lessonTemplateDescription,
                                mediaSaveDTO, duration));
                        if (MenuUtils.scanInt("Do you want to add one more lesson template? \n" +
                                "1. Yes\n" +
                                "or press any key: ") != 1)
                            addLessonTemplate = false;
                    }
                    topics.add(new TopicSaveDTO(topicName, topicDescription, lessons));
                    if (MenuUtils.scanInt("Do you want to add one more topic? \n" +
                            "1. Yes\n" +
                            "or press any key: ") != 1)
                        addTopic = false;
                }


                List<PriceSaveDTO> prices = new ArrayList<>();
                LookupDTO regular = lookupMapper.toDTO(lookupRepository.findOne(PriceType.REGULAR.getValue()));
                LookupDTO premium = lookupMapper.toDTO(lookupRepository.findOne(PriceType.PREMIUM.getValue()));
                prices.add(new PriceSaveDTO(regular, new BigDecimal(MenuUtils.scanLine("Please, input regular price: ")), null));
                prices.add(new PriceSaveDTO(premium, new BigDecimal(MenuUtils.scanLine("Please, input premium price: ")), null));

                courseDTO.setTopics(topics);
                courseDTO.setPrices(prices);
                courseController.createCourse(courseDTO);
                break;
            }


            case 2: {
                try {
                    CourseDTO courseDTO = findCourseDto();
                    CourseUpdateDTO courseUpdateDTO = new CourseUpdateDTO();
                    courseUpdateDTO.setCategory(courseDTO.getCategory());
                    courseUpdateDTO.setDescription(courseDTO.getDescription());
                    courseUpdateDTO.setActive(courseDTO.getActive());
                    courseUpdateDTO.setTopics(courseDTO.getTopics());
                    courseUpdateDTO.setName(courseDTO.getName());
                    courseUpdateDTO.setId(courseDTO.getId());
                    courseUpdateDTO.setRecommendedLiterature(courseDTO.getRecommendedLiterature());
                    courseUpdateDTO.setPrices(courseDTO.getPrices());
                    boolean flag = true;
                    while (flag) {
                        updatedMenu();
                        int option = MenuUtils.scanInt("Please, choose field that you want to update: ");
                        updateCourseDTO(courseUpdateDTO, option);
                        int update = MenuUtils.scanInt("Do you want update other fields? \n1.Yes\n Press any key");
                        if (update != 1) {
                            flag = false;
                        }
                    }
                    courseController.updateCourse(courseUpdateDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("There is no such course in our system");
                    return;
                }
                break;
            }

            case 3: {
                try {
                    courseController.closeCourse(findCourseDto());
                } catch (Exception e) {
                    System.out.println("There is no such course in our system");
                    return;
                }
            }
        }
    }


    private CourseDTO findCourseDto() throws Exception {
        long courseId = MenuUtils.scanInt("Please, enter course id: ");
        Course course = courseService.findById(courseId);
        if (course != null) {
            return courseMapper.toDTO(course);
        } else throw new Exception();

    }

    private static void menu() {
        MenuUtils.printMenu(
                "-1. Quit",
                "1. Create new course",
                "2. Update course",
                "3. Close course",
                "4. Remove topic",
                "5. Remove lesson template",
                "6. Find course by filter",
                "7. Find all courses"
        );
    }

    private static void updatedMenu() {
        MenuUtils.printMenu(
                "1.  Course name: ",
                "2.  Course recommended literature: ",
                "3.  Course category",
                "4.  Course description",
                "5.  Course certificate template",
                "6.  Course photo",
                "7.  Topic name",
                "8.  Topic description",
                "9.  Lesson template name",
                "10. Lesson template description",
                "11. Lesson template media",
                "12. Lesson duration"
        );
    }


    private static void printTopic(TopicDTO topic) {
        printField("Topic", topic.getId());
        printField("Topic name", topic.getName());
        printField("Topic description", topic.getDescription());
    }

    private static void printLessonTemplate(LessonTemplateDTO lessonTemplateDTO) {
        printField("Lesson template", lessonTemplateDTO.getId());
        printField("Lesson template title", lessonTemplateDTO.getName());
        printField("Lesson template description", lessonTemplateDTO.getDescription());
        printField("Lesson template duration", lessonTemplateDTO.getDuration());
    }

    private CourseUpdateDTO updateCourseDTO(CourseUpdateDTO courseDTO, int option) {
        switch (option) {
            case 1: {
                setCourseName(courseDTO);
                break;
            }
            case 2: {
                setRecommendedLiterature(courseDTO);
                break;
            }
            case 3: {
                setCategoryDTO(courseDTO);
                break;
            }
            case 4: {
                setCourseDescription(courseDTO);
                break;
            }
            case 5: {

            }
            case 6: {

            }
            case 7: {
                setTopicName(courseDTO);
                break;
            }
            case 8: {
                setTopicDescription(courseDTO);
                break;
            }
            case 9: {
                setLessonTemplateName(courseDTO);
                break;
            }
            case 10: {
                setLessonTemplateDescription(courseDTO);
                break;
            }
            case 11: {

            }
            case 12: {
                setLessonTemplateDuration(courseDTO);
                break;
            }
        }
        return courseDTO;
    }

    private void setRecommendedLiterature(CourseUpdateDTO courseDTO) {
        String recommendedLiterature = MenuUtils.scanLine("Please, input course recommended literature : ");
        courseDTO.setRecommendedLiterature(recommendedLiterature);
    }

    private void setRecommendedLiterature(CourseSaveDTO courseDTO) {
        String recommendedLiterature = MenuUtils.scanLine("Please, input course recommended literature : ");
        courseDTO.setRecommendedLiterature(recommendedLiterature);
    }

    private void setCourseName(CourseUpdateDTO courseDTO) {
        String name = MenuUtils.scanLine("Please, input new course name: ");
        courseDTO.setName(name);
    }

    private void setCourseName(CourseSaveDTO courseDTO) {
        String name = MenuUtils.scanLine("Please, input  course name: ");
        courseDTO.setName(name);
    }

    private void setCourseDescription(CourseUpdateDTO courseDTO) {
        String name = MenuUtils.scanLine("Please, input new course description: ");
        courseDTO.setDescription(name);
    }

    private void setCourseDescription(CourseSaveDTO courseDTO) {
        String name = MenuUtils.scanLine("Please, input  course description: ");
        courseDTO.setDescription(name);
    }

    private void setCategoryDTO(CourseUpdateDTO courseDTO) {
        long categoryId = MenuUtils.scanInt("Please, enter a category id: ");
        CategoryDTO categoryDTO = categoryMapper.toDTO(categoryRepository.findOne(categoryId));
        courseDTO.setCategory(categoryDTO);
    }

    private void setCategoryDTO(CourseSaveDTO courseDTO) {
        long categoryId = MenuUtils.scanInt("Please, enter a category id: ");
        CategoryDTO categoryDTO = categoryMapper.toDTO(categoryRepository.findOne(categoryId));
        courseDTO.setCategory(categoryDTO);
    }

    private void setTopicName(CourseUpdateDTO courseDTO) {
        int topicId = getTopicId(courseDTO);
        String topicName = MenuUtils.scanLine("Please, enter a topic name: ");
        courseDTO.getTopics().get(topicId).setName(topicName);
    }

    private void setTopicDescription(CourseUpdateDTO courseDTO) {
        int topicId = getTopicId(courseDTO);
        String topicDescription = MenuUtils.scanLine("Please, enter a topic description: ");
        courseDTO.getTopics().get(topicId).setDescription(topicDescription);
    }

    private int getTopicId(CourseUpdateDTO courseDTO) {
        for (TopicDTO topic : courseDTO.getTopics()) {
            MenuUtils.printField("topic number", courseDTO.getTopics().indexOf(topic) + 1);
            printTopic(topic);
        }
        return MenuUtils.scanInt("Please, enter a topic : ") -1;
    }

    private void setLessonTemplateName(CourseUpdateDTO courseDTO) {
        int topicId = getTopicId(courseDTO);
        int lessonTemplateId = getLessonId(courseDTO, topicId);
        String lessonTemplateName = MenuUtils.scanLine("Please, enter a lesson template name: ");
        courseDTO.getTopics().get(topicId).getLessonTemplates().get(lessonTemplateId).setName(lessonTemplateName);
    }

    private void setLessonTemplateDescription(CourseUpdateDTO courseDTO) {
        int topicId = getTopicId(courseDTO);
        int lessonTemplateId = getLessonId(courseDTO, topicId);

        String lessonTemplateDescription = MenuUtils.scanLine("Please, enter a lesson template description: ");
        courseDTO.getTopics().get(topicId).getLessonTemplates().get(lessonTemplateId).setDescription(lessonTemplateDescription);
    }

    private void setLessonTemplateDuration(CourseUpdateDTO courseDTO) {
        int topicId = getTopicId(courseDTO);
        int lessonTemplateId = getLessonId(courseDTO, topicId);

        int lessonTemplateDuration = MenuUtils.scanInt("Please, enter a lesson template duration: ");
        courseDTO.getTopics().get(topicId).getLessonTemplates().get(lessonTemplateId).setDuration(lessonTemplateDuration);
    }

    private int getLessonId(CourseUpdateDTO courseDTO, int topicId) {
        for (LessonTemplateDTO lessonTemplate : courseDTO.getTopics().get(topicId).getLessonTemplates()) {
            MenuUtils.printField("lesson template number", courseDTO.getTopics().get(topicId).getLessonTemplates().indexOf(lessonTemplate) + 1);
            printLessonTemplate(lessonTemplate);
        }
        return MenuUtils.scanInt("Please, enter a lesson template : ")-1;
    }

    private static MediaSaveDTO addMedia(String message) {
        String path = MenuUtils.scanLine(message);
        return new MediaSaveDTO(path);
    }

}
