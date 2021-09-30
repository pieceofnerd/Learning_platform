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
import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.LookupDTO;
import com.sytoss.web.dto.save.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class CourseMenu {

    private final CourseController courseController;

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final LookupRepository lookupRepository;

    private final CategoryMapper categoryMapper;

    private final CourseMapper courseMapper;

    private final LookupMapper lookupMapper;

    public CourseMenu(CourseController courseController, CategoryRepository categoryRepository,
                      CourseRepository courseRepository, LookupRepository lookupRepository, CategoryMapper categoryMapper,
                      CourseMapper courseMapper, LookupMapper lookupMapper) {
        this.courseController = courseController;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.lookupRepository = lookupRepository;
        this.categoryMapper = categoryMapper;
        this.courseMapper = courseMapper;
        this.lookupMapper = lookupMapper;
    }

    public void start() {
        menu();
        switch (MenuUtils.scanInt()) {
            case 1: {
                MediaSaveDTO coursePhoto = addMedia("Please enter a photo path: ");
                MediaSaveDTO certificateTemplate = addMedia("Please enter a certificate template: ");
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

                CourseDTO courseDTO = new CourseDTO();
                setCourseName(courseDTO);
                setRecommendedLiterature(courseDTO);
                setCategoryDTO(courseDTO);
                setCourseDescription(courseDTO);
//                courseController.createCourse(new CourseSaveDTO(name, recommendedLiterature, description, categoryDTO,
//                        addMedia("Please add certificate template: "), addMedia("Please add course photo: "), topics, prices));
                break;
            }


            case 2: {
                try {
                    CourseDTO courseDTO = findCourseDto();

                    boolean flag = true;
                    while (flag) {
                        updatedMenu();
                        int option = MenuUtils.scanInt("Please, choose field that you want to update: ");

                    }
                } catch (Exception e) {
                    System.out.println("There is no such course in our system");
                    return;
                }
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
        Course course = courseRepository.findOne(courseId);
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

    private CourseDTO updateCourseDTO(CourseDTO courseDTO, int option) {
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
                MediaSaveDTO certificateTemplate = addMedia("Please enter a certificate template: ");
                //courseDTO.setCertificateTemplate(certificateTemplate);
            }
            case 6: {

            }
            case 7: {

            }
        }
        return courseDTO;
    }

    private void setRecommendedLiterature(CourseDTO courseDTO) {
        String recommendedLiterature = MenuUtils.scanLine("Please, input course recommended literature : ");
        courseDTO.setRecommendedLiterature(recommendedLiterature);
    }

    private void setCourseName(CourseDTO courseDTO) {
        String name = MenuUtils.scanLine("Please, input new course name: ");
        courseDTO.setName(name);
    }

    private void setCourseDescription(CourseDTO courseDTO) {
        String description = MenuUtils.scanLine("Please, input new course description: ");
        courseDTO.setDescription(description);
    }

    private void setCategoryDTO(CourseDTO courseDTO) {
        long categoryId = MenuUtils.scanInt("Please, enter a category id: ");
        CategoryDTO categoryDTO = categoryMapper.toDTO(categoryRepository.findOne(categoryId));
        courseDTO.setCategory(categoryDTO);
    }

    private static MediaSaveDTO addMedia(String message) {
        String path = MenuUtils.scanLine(message);
        return new MediaSaveDTO(path);
    }

}
