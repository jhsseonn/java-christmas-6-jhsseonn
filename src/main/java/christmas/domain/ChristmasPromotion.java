package christmas.domain;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;
import christmas.consts.DecemberEventBadge;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChristmasPromotion implements ChristmasConsts {
    private Order orderHistory;
    private List<HashMap<String, Integer>> promotionResult;
    private int totalPromotionAmount;
    private DecemberEventBadge decemberEventBadge;

    public ChristmasPromotion(Order orderHistory){
        this.orderHistory = orderHistory;
        this.promotionResult = new ArrayList<>();
        this.totalPromotionAmount = 0;
        this.decemberEventBadge = DecemberEventBadge.NONE;
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

    public int addWeekEndEvent(Order order){
        int weekdayEventAmount = 0;
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(WEEKENDS.contains(orderDayOfWeek)){
            List<OrderMenu> orderMenus = order.getOrderMenus();
            weekdayEventAmount+=getWeekendEventAmount(orderMenus);
        }
        totalPromotionAmount+=weekdayEventAmount;
        return weekdayEventAmount;
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

    public int addSpecialEvent(Order order){
        int specialEventAmount = 0;
        int orderDate = order.getOrderDate().getDayOfMonth();
        DayOfWeek orderDayOfWeek = order.getOrderDayOfWeek();
        if(orderDayOfWeek.equals(DayOfWeek.SUNDAY) || orderDate==25){
            specialEventAmount+=1000;
            totalPromotionAmount+=1000;
        }
        return specialEventAmount;
    }

    public int addPresentationEvent(Order order){
        int presentationEventAmount = 0;
        order.computeTotalOrderAmount();
        int totalOrderAmount = order.getTotalOrderAmount();
        if (totalOrderAmount>=120000){
            presentationEventAmount+=25000;
            totalPromotionAmount+=25000;
        }
        return presentationEventAmount;
    }

    public void updateDecemberEventBadge(){
        if (totalPromotionAmount>=20000){
            decemberEventBadge=DecemberEventBadge.SANTA;
        } else if (totalPromotionAmount>=10000){
            decemberEventBadge=DecemberEventBadge.TREE;
        } else if (totalPromotionAmount>=5000){
            decemberEventBadge=DecemberEventBadge.STAR;
        }
    }

    public DecemberEventBadge getDecemberEventBadge(){
        return decemberEventBadge;
    }
}
