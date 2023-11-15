package christmas.service;

import christmas.consts.ChristmasMenu;
import christmas.consts.DecemberEventBadge;
import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.domain.OrderMenu;

import java.util.HashMap;
import java.util.List;

public class ChristmasService {

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
        christmasPromotion.updateDecemberEventBadge();
    }

    public int getExpectAmountAfterDiscount(ChristmasPromotion christmasPromotion){
        int totalDiscountAmount = christmasPromotion.getTotalPromotionAmount();
        int totalOrderAmount = christmasPromotion.getOrderHistory().getTotalOrderAmount();
        return totalOrderAmount-totalDiscountAmount;
    }
}
