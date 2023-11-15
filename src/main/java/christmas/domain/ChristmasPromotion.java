package christmas.domain;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;
import christmas.consts.ChristmasPromotionEvents;
import christmas.consts.DecemberEventBadge;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ChristmasPromotion implements ChristmasConsts {
    private Order orderHistory;
    private HashMap<ChristmasPromotionEvents, Integer> promotionResult;
    private int totalPromotionAmount;
    private DecemberEventBadge decemberEventBadge;

    public ChristmasPromotion(Order orderHistory){
        this.orderHistory = orderHistory;
        this.promotionResult = new HashMap<>();
        this.totalPromotionAmount = INTEGER_RESET;
        this.decemberEventBadge = DecemberEventBadge.NONE;
    }

    public Order getOrderHistory(){
        return orderHistory;
    }

    public HashMap<ChristmasPromotionEvents, Integer> getPromotionResult(){
        return promotionResult;
    }

    public void addChristmasDDayEvent(LocalDate localDate){
        final int day = localDate.getDayOfMonth();
        int ddayPromotionAmount = INTEGER_RESET;
        if (validDayForDDayEvent(localDate)){
            ddayPromotionAmount = DDAY_PROMOTION_START_DISCOUNT+DDAY_PROMOTION_DAY_DISCOUNT*(day-1);
        }
        promotionResult.put(ChristmasPromotionEvents.CHRISTMAS_DDAY_PROMOTION, ddayPromotionAmount);
    }

    public int getTotalPromotionAmount(){
        return totalPromotionAmount;
    }

    public void addWeekDayEvent(Order order){
        int weekdayEventAmount = INTEGER_RESET;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKDAYS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekdayEventAmount+=getWeekdayEventAmount(orderMenus);
            promotionResult.put(ChristmasPromotionEvents.WEEKDAY_PROMOTION, weekdayEventAmount);
        }
    }

    public int getWeekdayEventAmount(List<OrderMenu> orderMenus){
        int weekdayEventAmount = INTEGER_RESET;
        for(OrderMenu menu:orderMenus){
            if(menu.getChristmasMenu()==ChristmasMenu.DESSERT){
                weekdayEventAmount+=WEEKDAY_AND_WEEKEND_DISCOUNT*menu.getOrderMenuCount();
            }
        }
        return weekdayEventAmount;
    }

    public void addWeekEndEvent(Order order){
        int weekendEventAmount = INTEGER_RESET;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKENDS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekendEventAmount+=getWeekendEventAmount(orderMenus);
            promotionResult.put(ChristmasPromotionEvents.WEEKEND_PROMOTION, weekendEventAmount);
        }
    }

    public int getWeekendEventAmount(List<OrderMenu> orderMenus){
        int weekdayEventAmount = INTEGER_RESET;
        for(OrderMenu menu:orderMenus){
            if(menu.getChristmasMenu()==ChristmasMenu.MAIN){
                weekdayEventAmount+=WEEKDAY_AND_WEEKEND_DISCOUNT*menu.getOrderMenuCount();
            }
        }

        return weekdayEventAmount;
    }

    public void addSpecialEvent(Order order){
        int specialEventAmount = INTEGER_RESET;
        int orderDate = order.getOrderDate().getDayOfMonth();
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(orderDayOfWeek.equals(DayOfWeek.SUNDAY) || orderDate==CHRISTMAS){
            specialEventAmount+=SPECIAL_EVENT_DISCOUNT;
            promotionResult.put(ChristmasPromotionEvents.SPECIAL_PROMOTION, specialEventAmount);
        }
    }

    public void addPresentationEvent(Order order){
        int presentationEventAmount = INTEGER_RESET;
        order.computeTotalOrderAmount();
        int totalOrderAmount = order.getTotalOrderAmount();
        if (totalOrderAmount>=PRESENTATION_DISCOUNT_AMOUNT){
            presentationEventAmount+=CHAMPAGNE_AMOUNT;
            promotionResult.put(ChristmasPromotionEvents.PRESENTATION_PROMOTION, presentationEventAmount);
        }
    }

    public void updateTotalPromotionAmount(){
        promotionResult.forEach((key, value) -> {
            totalPromotionAmount+=value;
        });
    }

    public void updateDecemberEventBadge(){
        if (totalPromotionAmount>=DECEMBER_EVENT_BADGE_SANTA_AMOUNT){
            decemberEventBadge=DecemberEventBadge.SANTA;
        } else if (totalPromotionAmount>=DECEMBER_EVENT_BADGE_TREE_AMOUNT){
            decemberEventBadge=DecemberEventBadge.TREE;
        } else if (totalPromotionAmount>=DECEMBER_EVENT_BADGE_STAR_AMOUNT){
            decemberEventBadge=DecemberEventBadge.STAR;
        } else if (totalPromotionAmount<DECEMBER_EVENT_BADGE_STAR_AMOUNT){
            decemberEventBadge=DecemberEventBadge.NONE;
        }
    }

    public DecemberEventBadge getDecemberEventBadge(){
        return decemberEventBadge;
    }

    public boolean validDayForDDayEvent(LocalDate orderDate){
        if(orderDate.getDayOfMonth()>CHRISTMAS){
            return false;
        }
        return true;
    }
}
