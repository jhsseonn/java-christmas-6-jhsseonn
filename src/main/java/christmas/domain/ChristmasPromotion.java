package christmas.domain;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChristmasPromotion implements ChristmasConsts {
    private Order orderHistory;
    private List<HashMap<String, Integer>> promotionResult;
    private int totalPromotionAmount;

    public ChristmasPromotion(Order orderHistory){
        this.orderHistory = orderHistory;
        this.promotionResult = new ArrayList<>();
        this.totalPromotionAmount = 0;
    }

    public int addChristmasDDayEvent(LocalDate localDate){
        final int day = localDate.getDayOfMonth();
        final int ddayPromotionAmount = 1000+100*(day-1);
        totalPromotionAmount+=ddayPromotionAmount;
        return ddayPromotionAmount;
    }

    public int getTotalPromotionAmount(){
        return totalPromotionAmount;
    }

    public int addWeekDayEvent(Order order){
        int weekdayEventAmount = 0;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKDAYS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekdayEventAmount+=getWeekdayEventAmount(orderMenus);
        }
        totalPromotionAmount+=weekdayEventAmount;
        return weekdayEventAmount;
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
}
