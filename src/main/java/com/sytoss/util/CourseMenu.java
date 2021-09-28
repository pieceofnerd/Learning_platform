package com.sytoss.util;

import com.sytoss.mapper.CategoryMapper;
import com.sytoss.repository.course.CategoryRepository;
import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.TopicDTO;
import com.sytoss.web.dto.save.LessonTemplateSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;

import java.util.ArrayList;
import java.util.List;

public class CourseMenu {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CourseMenu(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public void start() {
        menu();
        switch (MenuUtils.scanInt()) {
            case 1: {
                String name = MenuUtils.scanLine("Please, enter a course name: ");
                String recommendedLiterature = MenuUtils.scanLine("Please, enter a recommended literature: ");
                MediaSaveDTO coursePhoto = addMedia("Please enter a photo path: ");
                MediaSaveDTO certificateTemplate = addMedia("Please enter a certificate template: ");
                String description = MenuUtils.scanLine("Please, enter an description: ");
                long categoryId = MenuUtils.scanInt("Please, enter a category id");

                CategoryDTO categoryDTO = categoryMapper.toDTO(categoryRepository.findOne(categoryId));
                List<TopicDTO> topics = new ArrayList<>();

                boolean addTopic = true;

                while (addTopic) {
                    boolean addLessonTemplate = true;
                    List<LessonTemplateSaveDTO> lessons = new ArrayList<>();
                    String topicName = MenuUtils.scanLine("Please, enter a topic name: ");
                    String topicDescription = MenuUtils.scanLine("Please, enter an topic description: ");
                    while (addLessonTemplate) {
                        String lessonTemplateName = MenuUtils.scanLine("Please, enter a lesson template name: ");
                        String lessonTemplateDescription = MenuUtils.scanLine("Please, enter an topic description: ");
                        int duration = MenuUtils.scanInt("Please, enter lesson duration in minutes: ");
                        MediaSaveDTO mediaSaveDTO = addMedia("Please, enter a path to content: ");
                        lessons.add(new LessonTemplateSaveDTO(name, description,
                                mediaSaveDTO, duration));
                        System.out.println("Do you want to add one more lesson template? \n" +
                                "1. Yes" +
                                "or press any key");
                    }
                }

                break;
            }
        }
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

    private static MediaSaveDTO addMedia(String message) {
        String path = MenuUtils.scanLine(message);
        return new MediaSaveDTO(path);
    }

}
