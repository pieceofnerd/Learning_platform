package com.sytoss;

import com.sytoss.config.Config;
import com.sytoss.util.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Run {

    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) throws Exception {

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
//
//        Promotion promotion = new Promotion();
//        promotion.setPromotionState(context.getBean(LookupRepository.class).findOne(6L));
//        promotion.setName("TEST");
//        promotion.setPercent(5);
//        promotion.setStartDate(dateFormat.parse("2021-01-10 05:00 PM"));
//        promotion.setEndDate(dateFormat.parse("2021-01-20 10:00 AM"));
//        PromotionService promotionService = context.getBean(PromotionService.class);
//        promotionService.createPromotion(promotion);
//        System.out.println(dateFormat.parse("2021-01-10 12:00 PM"));
        Menu menu = context.getBean(Menu.class);
        menu.start();


    }
}