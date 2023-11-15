package christmas.service;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasPromotionEvents;
import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.util.ChristmasUtil;

import java.util.HashMap;
import java.util.List;

public class ChristmasService implements ChristmasConsts {

    private ChristmasUtil christmasUtil = new ChristmasUtil();

    public int getExpectedVisitDay(String inputVisitDay){
        int expectedVisitDay = isValidVisitDay(inputVisitDay);
        isValidRangeVisitDay(expectedVisitDay);
        return expectedVisitDay;
    }

    public HashMap<String, Integer> getTotalOrderMenu(Order order){
        List<OrderMenu> orderMenus = order.getOrderMenus();
        HashMap<String, Integer> orderMenuPreview = new HashMap<>();
        for(OrderMenu orderMenu:orderMenus){
            orderMenuPreview.put(orderMenu.getMenu(), orderMenu.getOrderMenuCount());
        }
        return orderMenuPreview;
    }

    public void confirmEvents(ChristmasPromotion christmasPromotion){
        Order orderHistory = christmasPromotion.getOrderHistory();
        christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        christmasPromotion.addWeekDayEvent(orderHistory);
        christmasPromotion.addWeekEndEvent(orderHistory);
        christmasPromotion.addSpecialEvent(orderHistory);
        christmasPromotion.addPresentationEvent(orderHistory);
        christmasPromotion.updateTotalPromotionAmount();
        christmasPromotion.updateDecemberEventBadge();
    }

    public int getExpectAmountAfterDiscount(ChristmasPromotion christmasPromotion){
        int totalDiscountAmount = christmasPromotion.getTotalPromotionAmount();
        int totalOrderAmount = christmasPromotion.getOrderHistory().getTotalOrderAmount();
        if (christmasPromotion.getPromotionResult().containsKey(ChristmasPromotionEvents.PRESENTATION_PROMOTION)){
            totalDiscountAmount-= CHAMPAGNE_AMOUNT;
        }
        return totalOrderAmount-totalDiscountAmount;
    }

    public int isValidVisitDay(String inputVisitDay) throws NumberFormatException{
        int expectedVisitDay = Integer.parseInt(inputVisitDay);
        return expectedVisitDay;
    }

    public void isValidRangeVisitDay(int inputDay) throws IllegalArgumentException{
        if (inputDay<EVENT_START_DAY || inputDay>EVENT_END_DAY) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> getInputOrderMenus(String inputOrderMenus) throws IllegalArgumentException{
        validateInputSplit(inputOrderMenus);
        List<String> orderMenus = List.of(inputOrderMenus.split(SPLIT_INPUT_STRING));
        return orderMenus;
    }

    public void validateInputSplit(String input) throws IllegalArgumentException{
        if(!input.contains(SPLIT_INPUT_STRING) && christmasUtil.countChar(input, SPLIT_INPUT_MENU_CHAR)!=1){
            throw new IllegalArgumentException();
        }
    }
}
