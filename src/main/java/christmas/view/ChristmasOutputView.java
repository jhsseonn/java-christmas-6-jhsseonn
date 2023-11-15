package christmas.view;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasPromotionEvents;
import java.text.DecimalFormat;
import java.util.HashMap;

public class ChristmasOutputView implements ChristmasConsts {
    DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);

    public void printWelcome(){
        System.out.println(DECEMBER_EVENT_WELCOME);
    }

    public void printChristmasPromotionPreview(int orderDay){
        System.out.printf(CHRISTMAS_PROMOTION_PREVIEW, orderDay);
    }

    public void printOrderMenus(HashMap<String, Integer> orderMenuPreview){
        System.out.println(ORDER_MENU);
        orderMenuPreview.forEach((key, value) -> {
            System.out.printf(ORDER_MENUS_FORMAT, key, df.format(value));
        });
        System.out.println();
    }

    public void printTotalOrderAmount(int totalOrderAmount){
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT);
        System.out.printf(ORDER_AMOUNT_FORMAT, df.format(totalOrderAmount));
    }

    public void printPresentationEventMenu(){
        System.out.println(PRESENTATION_MENU);
        System.out.printf(PRESENTATION_EVENT_FORMAT, PRESENTATION_PROMOTION_MENU, PRESENTATION_PROMOTION_COUNT);
    }

    public void printPromotionResult(HashMap<ChristmasPromotionEvents, Integer> promotionResult){
        System.out.println(PROMOTIONS);
        promotionResult.forEach((key, value) -> {
            System.out.printf(PROMOTION_RESULT_FORMAT, key.getPromotionName(), df.format(value));
        });
        System.out.println();
    }

    public void printPromotionResultNone(){
        System.out.println(PROMOTIONS);
        System.out.println(NONE_FORMAT);
    }

    public void printTotalPromotionAmount(int totalPromotionAmount){
        System.out.println(TOTAL_PROMOTION_AMOUNT);
        System.out.printf(TOTAL_PROMOTION_AMOUNT_FORMAT, df.format(totalPromotionAmount));
    }

    public void printTotalPromotionAmountNone(){
        System.out.println(TOTAL_PROMOTION_AMOUNT);
        System.out.println(NONE_FORMAT);
    }

    public void printExpectAmountAfterDiscount(int expectAmountAfterDiscount){
        System.out.println(EXPECT_AMOUNT_AFTER_DISCOUNT);
        System.out.printf(ORDER_AMOUNT_FORMAT, df.format(expectAmountAfterDiscount));
    }

    public void printDecemberEventBadge(String decemberEventBadge){
        System.out.println(DECEMBER_EVENT_BADGE);
        System.out.println(decemberEventBadge);
    }
}
