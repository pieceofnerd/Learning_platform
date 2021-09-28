package com.sytoss.util;

import com.sytoss.controller.LessonController;
import com.sytoss.mapper.*;
import com.sytoss.model.Media;
import com.sytoss.repository.MediaRepository;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.LessonTemplateRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.CommunicationDTO;
import com.sytoss.web.dto.HomeTaskDTO;
import com.sytoss.web.dto.LessonDTO;
import com.sytoss.web.dto.MediaDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;
import com.sytoss.web.dto.save.HomeTaskSaveDTO;
import com.sytoss.web.dto.save.LessonSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.sytoss.util.MenuUtils.*;

@Component
@Transactional
public class LessonMenu {

    private final LessonController lessonController;

    private final LessonMapper lessonMapper;

    private final UserAccountMapper userAccountMapper;

    private final LessonTemplateMapper lessonTemplateMapper;

    private final MediaMapper mediaMapper;

    private final StudyGroupMapper studyGroupMapper;

    private final UserAccountRepository userAccountRepository;

    private final MediaRepository mediaRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final LessonRepository lessonRepository;

    private final StudyGroupRepository studyGroupRepository;

    @Autowired
    public LessonMenu(LessonController lessonController, LessonMapper lessonMapper,
                      UserAccountMapper userAccountMapper, LessonTemplateMapper lessonTemplateMapper,
                      MediaMapper mediaMapper, StudyGroupMapper studyGroupMapper, UserAccountRepository userAccountRepository,
                      MediaRepository mediaRepository, LessonTemplateRepository lessonTemplateRepository, LessonRepository lessonRepository,
                      StudyGroupRepository studyGroupRepository) {
        this.lessonController = lessonController;
        this.lessonMapper = lessonMapper;
        this.userAccountMapper = userAccountMapper;
        this.lessonTemplateMapper = lessonTemplateMapper;
        this.mediaMapper = mediaMapper;
        this.studyGroupMapper = studyGroupMapper;
        this.userAccountRepository = userAccountRepository;
        this.mediaRepository = mediaRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lessonRepository = lessonRepository;
        this.studyGroupRepository = studyGroupRepository;
    }

    public void start() {
        menu();
        switch (scanInt()) {
            case -1:
                return;
            case 1: {
                long mentorId = scanInt("Write mentor id:  ");
                long studyGroupId = scanInt("Write study group id: ");
                long lessonTemplateId = scanInt("Write lesson template id: ");
                Date lessonDate;
                try {
                    printNoLessons("Enter a lesson time: ");
                    lessonDate = scanDate();
                } catch (ParseException e) {
                    printNoLessons("You entered incorrect date. Please, try again");
                    return;
                }
                HomeTaskSaveDTO homeTaskSaveDTO;
                try {
                    homeTaskSaveDTO = addHomeTask();
                } catch (ParseException e) {
                    printNoLessons("You entered incorrect date. Please, try again");
                    return;
                }
                LessonSaveDTO lessonDTO = new LessonSaveDTO(userAccountMapper.toDTO(userAccountRepository.findOne(mentorId)),
                        homeTaskSaveDTO,
                        lessonTemplateMapper.toDTO(lessonTemplateRepository.findOne(lessonTemplateId)),
                        studyGroupMapper.toDTO(studyGroupRepository.findById(studyGroupId)),
                        lessonDate);

                LessonDTO response = lessonController.createLesson(lessonDTO);
                System.out.println("Current lesson: ");
                printLesson(response);
                break;
            }
            case 2: {
                long lessonId = scanInt("Enter lesson id: ");
                long mentorId = scanInt("Write mentor id:  ");
                String task = scanLine("Write home task: ");
                String path = scanLine("Write home task path: ");
                Date lessonDate;
                Date deadline;
                try {
                    System.out.println("Enter a lesson time: ");
                    lessonDate = scanDate();
                    System.out.println("Enter a deadline time: ");
                    deadline = scanDate();
                } catch (ParseException e) {
                    System.out.println("You entered incorrect date. Please, try again");
                    return;
                }
                LessonDTO lessonDTO = lessonMapper.toDTO(lessonRepository.findOne(lessonId));
                MediaDTO mediaDTO = lessonDTO.getHomeTask().getFilePath();
                mediaDTO.setPath(path);

                HomeTaskDTO updateHomeTaskDTO = new HomeTaskDTO(lessonDTO.getHomeTask().getId(), task,
                        mediaDTO, deadline);

                LessonDTO updatedLessonDto = new LessonDTO(lessonId,
                        userAccountMapper.toDTO(userAccountRepository.findOne(mentorId)),
                        updateHomeTaskDTO,
                        lessonDTO.getLessonTemplate(),
                        lessonDTO.getStudyGroup(),
                        null,
                        lessonDate,
                        lessonDTO.getCreatedDate(),
                        null);

                lessonController.updateLesson(updatedLessonDto);
                break;
            }
            case 3: {
                long lessonId = scanInt("Enter lesson id: ");
                lessonController.deleteLesson(lessonMapper.toDTO(lessonRepository.findOne(lessonId)));
                System.out.println("Lesson " + lessonId + " was deleted");
                break;
            }
            case 4: {
                long lessonId = scanInt("Enter lesson id: ");
                lessonController.deleteAllComments(lessonMapper.toDTO(lessonRepository.findById(lessonId)));
                System.out.println("All comments under lesson " + lessonId + " were deleted");
                break;
            }
            case 5: {
                printLessonFiltersOptions();
                int option = scanInt();
                switch (option) {
                    case 1: {
                        Date firstDate;
                        Date secondDate;
                        try {
                            System.out.println("Enter first date ");
                            firstDate = scanDate();
                            System.out.println("Enter second date ");
                            secondDate = scanDate();
                        } catch (ParseException e) {
                            System.out.println("You entered incorrect date. Please, try again");
                            return;
                        }
                        long studyGroupId = scanInt("Enter a study group id: ");
                        List<LessonDTO> lessons = lessonController.findLessonsByFilter(new FilterLessonDTO(Filter.TIME_PERIOD, studyGroupId,
                                null, null, firstDate, secondDate));
                        printLessons(lessons);
                        break;
                    }
                    case 2: {
                        long studyGroupId = scanInt("Enter a study group id: ");
                        List<LessonDTO> lessons = lessonController.findLessonsByFilter(
                                new FilterLessonDTO(Filter.FUTURE_LESSONS_FOR_STUDY_GROUP,
                                        studyGroupId, null, null, null, null));
                        printLessons(lessons);
                        break;
                    }
                    case 3: {
                        long lessonTemplateId = scanInt("Enter a lesson template id: ");
                        List<LessonDTO> lessons = lessonController.findLessonsByFilter(
                                new FilterLessonDTO(Filter.LESSON_TEMPLATE,
                                        null, lessonTemplateId, null, null, null));
                        printLessons(lessons);
                        break;
                    }
                    case 4: {
                        long mentorId = scanInt("Enter a mentor id: ");
                        List<LessonDTO> lessons = lessonController.findLessonsByFilter(
                                new FilterLessonDTO(Filter.MENTOR,
                                        null, null, mentorId, null, null));
                        printLessons(lessons);
                        break;
                    }
                    default:
                        return;
                }
                break;
            }
            case 6: {
                printCommentFiltersOptions();
                int option = scanInt();
                long lessonId = scanInt("Enter a lesson id: ");
                List<CommunicationDTO> comments;
                switch (option) {
                    case 1: {
                        comments = lessonController.findCommentsByFilter(new FilterCommunicationDTO(
                                Filter.OLDEST, lessonId, null, null));
                        break;
                    }
                    case 2: {
                        comments = lessonController.findCommentsByFilter(new FilterCommunicationDTO(
                                Filter.NEWEST, lessonId, null, null));
                        break;
                    }
                    default:
                        return;
                }
                printComments(comments);
                break;
            }
        }
    }


    private void printLessons(List<LessonDTO> lessons) {
        if (!lessons.isEmpty()) {
            for (LessonDTO lesson : lessons) {
                System.out.println(" Lesson " + lesson.getId());
                printLesson(lesson);
            }
        } else {
            printNoSuchLessons();
        }
    }

    private void printComments(List<CommunicationDTO> comments) {
        if (!comments.isEmpty()) {
            for (CommunicationDTO comment : comments) {
                System.out.println("Comment " + comment.getId());
                printComment(comment);
            }
        } else System.out.println("There are no comments");
    }

    private void printNoSuchLessons() {
        printNoLessons("There are no lessons by this filter");
    }

    private void printNoLessons(String s) {
        System.out.println(s);
    }

    private static void printLessonFiltersOptions() {
        System.out.println("Choose filter: \n" +
                "1. Time period for study group \n" +
                "2. Future lesson for study group \n" +
                "3. Lesson template \n" +
                "4. Mentor \n");
    }

    private static void printCommentFiltersOptions() {
        System.out.println("Choose filter: \n" +
                "1. Sort comments  ascending \n" +
                "2. Sort comments  descending \n");
    }

    private static void printLesson(LessonDTO lesson) {
        printField("Mentor", lesson.getMentor().getId());
        printField("Home task", lesson.getHomeTask().getId());
        printField("Lesson Template", lesson.getLessonTemplate().getId());
        printField("Study group", lesson.getStudyGroup().getId());
        printField("Lesson date", lesson.getLessonDate());
        printField("Created date", lesson.getCreatedDate());
        printField("Updated date", lesson.getUpdatedDate());
    }

    private static void printComment(CommunicationDTO comment) {
        printField("Sender", comment.getSender().getId());
        printField("Date", comment.getSendDate());
        printField("Content", comment.getContent());
        printField("Updated date", comment.getUpdateDate());
        printField("Lesson", comment.getLesson().getId());
    }

    private void menu() {
        MenuUtils.printMenu(
                "-1. Quit",
                "1. Create new lesson",
                "2. Update lesson",
                "3. Delete lesson",
                "4. Hide all comments",
                "5. Find lesson by filter",
                "6. Find comments by filter"
        );
    }

    private HomeTaskSaveDTO addHomeTask() throws ParseException {
        String task = scanLine("Please, enter a task: ");
        MediaSaveDTO mediaSaveDTO = addMedia();
        Media media = mediaRepository.save(mediaMapper.toEntity(mediaSaveDTO));
        System.out.println("Enter homework deadline: ");
        Date date = scanDate();
        return new HomeTaskSaveDTO(task,
                mediaMapper.toDTO(mediaRepository.findOne(media.getId())),
                date);
    }

    private MediaSaveDTO addMedia() {
        String file_path = scanLine("Please, enter a path to file: ");
        return new MediaSaveDTO(file_path);
    }

}
