package com.sytoss.util;

import com.sytoss.controller.CourseController;
import com.sytoss.controller.PromotionController;
import com.sytoss.mapper.CourseMapper;
import com.sytoss.mapper.PromotionMapper;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.PromotionRepository;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.PromotionDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterPromotionDTO;
import com.sytoss.web.dto.save.PromotionSaveDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class PromotionMenu {

    private final PromotionController promotionController;

    private final CourseController courseController;

    private final CourseRepository courseRepository;

    private final LookupRepository lookupRepository;

    private final CourseMapper courseMapper;

    private final PromotionMapper promotionMapper;

    private final PromotionRepository promotionRepository;

    public PromotionMenu(PromotionController promotionController, CourseController courseController, CourseRepository courseRepository,
                         LookupRepository lookupRepository, CourseMapper courseMapper, PromotionMapper promotionMapper, PromotionRepository promotionRepository) {
        this.promotionController = promotionController;
        this.courseController = courseController;
        this.courseRepository = courseRepository;
        this.lookupRepository = lookupRepository;
        this.courseMapper = courseMapper;
        this.promotionMapper = promotionMapper;
        this.promotionRepository = promotionRepository;
    }

    public void start() {
        menu();
        switch (MenuUtils.scanInt()) {
            case 1:
                createPromotion();
                break;
            case 2:
                updatePromotion();
                break;
            case 3:
                findPromotionByFilter();
                break;
        }
    }

    private void menu() {
        MenuUtils.printMenu(
                "-1. Quit",
                "1. Create new promotion",
                "2. Update a promotion",
                "3. Find promotions by filter"
        );
    }

    private void filterMenu() {
        MenuUtils.printMenu(
                "-1. Quit",
                "1. Time period",
                "2. Promotion state"
        );
    }

    private void updatedMenu() {
        MenuUtils.printMenu(
                "-1 Quit",
                "1. Name",
                "2. Percent",
                "3. Start date",
                "4. End date"
        );
    }

    private void promotionStateMenu() {
        MenuUtils.printMenu(
                "1. Announced",
                "2. Active",
                "3. Finished"
        );
    }

    private void printPromotion(PromotionDTO promotionDTO) {
        MenuUtils.printField("Promotion id: ", promotionDTO.getId());
        MenuUtils.printField("Promotion name: ", promotionDTO.getName());
        MenuUtils.printField("Promotion percent: ", promotionDTO.getPercent());
        MenuUtils.printField("Promotion start date: ", promotionDTO.getStartDate());
        MenuUtils.printField("Promotion end date: ", promotionDTO.getEndDate());
        MenuUtils.printField("Promotion state: ", promotionDTO.getPromotionState().getValue());
        System.out.println();
    }

    private void createPromotion() {
        String promotionName = MenuUtils.scanLine("Please, enter promotion name: ");
        int percent = 0;
        while (percent <= 0 || percent >= 100) {
            percent = MenuUtils.scanInt("Please, enter discount percentage in bound 0-100: ");
        }
        Date startDate;
        Date endDate;
        try {
            startDate = MenuUtils.scanDate("Please, enter start date of promotion: ");
            endDate = MenuUtils.scanDate("Please, enter end date of promotion: ");
        } catch (ParseException e) {
            System.out.println("Something went wrong during parsing dates. Please, try again");
            return;
        }


        for (CourseDTO courseDTO : courseController.getAll()) {
            CourseMenu.printCourse(courseDTO);
        }

        List<Long> coursesId = new ArrayList<>();
        String input = MenuUtils.scanLine("Please, enter a course id in order to apply promotion separate values by coma: ");
        input = input.trim();
        String[] values = input.split("\\s+");
        for (String value : values) {
            coursesId.add(Long.valueOf(value));
        }

        List<PriceDTO> prices = new ArrayList<>();

        for (Long id : coursesId) {
            prices.addAll(courseMapper.toDTO(courseRepository.findById(id)).getPrices());
        }

        PromotionSaveDTO promotionSaveDTO = new PromotionSaveDTO(promotionName, percent, startDate, endDate, prices);

        promotionController.createPromotion(promotionSaveDTO);
    }

    private void updatePromotion() {
        List<PromotionDTO> promotionDTOS = promotionController.findPromotionsByFilter(new FilterPromotionDTO(Filter.NEWEST, null, null, null));
        for (PromotionDTO promotionDTO : promotionDTOS) {
            printPromotion(promotionDTO);
        }

        long id = MenuUtils.scanInt("Please, enter a promotion id: ");
        PromotionDTO promotion = promotionMapper.toDTO(promotionRepository.findOne(id));

        while (true) {
            updatedMenu();
            switch (MenuUtils.scanInt("Please, choose option: ")) {
                case 1:
                    promotion.setName(MenuUtils.scanLine("Enter name: "));
                    break;
                case 2:
                    int percent = MenuUtils.scanInt("Enter a percent: ");
                    promotion.setPercent(percent);
                    break;
                case 3:
                    try {
                        Date startDate = MenuUtils.scanDate("Enter a start date: ");
                        promotion.setStartDate(startDate);
                        break;
                    } catch (ParseException e) {
                        return;
                    }

                case 4:
                    try {
                        Date startDate = MenuUtils.scanDate("Enter an end date: ");
                        promotion.setStartDate(startDate);
                        break;
                    } catch (ParseException e) {
                        return;
                    }
            }
            System.out.println("Do you want to continue update?\n1. Yes\nOr press any number key");
            if (MenuUtils.scanInt() != 1)
                break;
        }


        promotionController.updatePromotion(promotion);

    }

    private void findPromotionByFilter() {
        filterMenu();
        int filter = MenuUtils.scanInt("Please, enter a value: ");

        switch (filter) {
            case 1:
                try {
                    Date startDate = MenuUtils.scanDate("Please, enter a start date: ");
                    Date endDate = MenuUtils.scanDate("Please, enter an end date: ");
                    promotionController.findPromotionsByFilter(
                            new FilterPromotionDTO(Filter.TIME_PERIOD, startDate, endDate, null));
                } catch (ParseException e) {
                    System.out.println("Please, try again");
                }
                break;
            case 2:
                promotionStateMenu();
                int option = MenuUtils.scanInt("Please, enter a value: ");
                long id;
                switch (option) {
                    case 1:
                        id = 20;
                        break;
                    case 2:
                        id = 21;
                        break;
                    case 3:
                        id = 22;
                        break;
                    default:
                        return;
                }
                List<PromotionDTO> promotions = promotionController.findPromotionsByFilter(
                        new FilterPromotionDTO(Filter.PROMOTION_STATE, null, null, id));

                for (PromotionDTO promotion : promotions) {
                    printPromotion(promotion);
                }
        }
    }

}
