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
        this.totalPromotionAmount = 0;
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
        int ddayPromotionAmount = 0;
        if (validDayForDDayEvent(localDate)){
            ddayPromotionAmount = 1000+100*(day-1);
        }
        promotionResult.put(ChristmasPromotionEvents.CHRISTMAS_DDAY_PROMOTION, ddayPromotionAmount);
    }

    public int getTotalPromotionAmount(){
        return totalPromotionAmount;
    }

    public void addWeekDayEvent(Order order){
        int weekdayEventAmount = 0;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKDAYS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekdayEventAmount+=getWeekdayEventAmount(orderMenus);
            promotionResult.put(ChristmasPromotionEvents.WEEKDAY_PROMOTION, weekdayEventAmount);
        }
    }

    public int getWeekdayEventAmount(List<OrderMenu> orderMenus){
        int weekdayEventAmount = 0;
        for(OrderMenu menu:orderMenus){
            if(menu.getChristmasMenu()==ChristmasMenu.DESSERT){
                weekdayEventAmount+=2023*menu.getOrderMenuCount();
            }
        }
        return weekdayEventAmount;
    }

    public void addWeekEndEvent(Order order){
        int weekendEventAmount = 0;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKENDS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekendEventAmount+=getWeekendEventAmount(orderMenus);
            promotionResult.put(ChristmasPromotionEvents.WEEKEND_PROMOTION, weekendEventAmount);
        }
    }

    public int getWeekendEventAmount(List<OrderMenu> orderMenus){
        int weekdayEventAmount = 0;
        for(OrderMenu menu:orderMenus){
            if(menu.getChristmasMenu()==ChristmasMenu.MAIN){
                weekdayEventAmount+=2023*menu.getOrderMenuCount();
            }
        }

        return weekdayEventAmount;
    }

    public void addSpecialEvent(Order order){
        int specialEventAmount = 0;
        int orderDate = order.getOrderDate().getDayOfMonth();
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(orderDayOfWeek.equals(DayOfWeek.SUNDAY) || orderDate==25){
            specialEventAmount+=1000;
            promotionResult.put(ChristmasPromotionEvents.SPECIAL_PROMOTION, specialEventAmount);
        }
    }

    public void addPresentationEvent(Order order){
        int presentationEventAmount = 0;
        order.computeTotalOrderAmount();
        int totalOrderAmount = order.getTotalOrderAmount();
        if (totalOrderAmount>=120000){
            presentationEventAmount+=25000;
            promotionResult.put(ChristmasPromotionEvents.PRESENTATION_PROMOTION, presentationEventAmount);
        }
    }

    public void updateTotalPromotionAmount(){
        promotionResult.forEach((key, value) -> {
            totalPromotionAmount+=value;
        });
    }

    public void updateDecemberEventBadge(){
        if (totalPromotionAmount>=20000){
            decemberEventBadge=DecemberEventBadge.SANTA;
        } else if (totalPromotionAmount>=10000){
            decemberEventBadge=DecemberEventBadge.TREE;
        } else if (totalPromotionAmount>=5000){
            decemberEventBadge=DecemberEventBadge.STAR;
        } else if (totalPromotionAmount<5000){
            decemberEventBadge=DecemberEventBadge.NONE;
        }
    }

    public DecemberEventBadge getDecemberEventBadge(){
        return decemberEventBadge;
    }

    public boolean validDayForDDayEvent(LocalDate orderDate){
        if(orderDate.getDayOfMonth()>25){
            return false;
        }
        return true;
    }
}
