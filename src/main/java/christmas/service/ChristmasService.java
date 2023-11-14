package christmas.service;

import christmas.consts.ChristmasMenu;
import christmas.domain.Order;
import christmas.domain.OrderMenu;

import java.util.HashMap;
import java.util.List;

public class ChristmasService {

    public void computeTotalOrderAmount(Order order){
        List<OrderMenu> orderMenus = order.getOrderMenus();
        int totalOrderAmount=0;
        for(OrderMenu menu:orderMenus){
            ChristmasMenu christmasMenu = menu.getChristmasMenu();
            String menuName = menu.getMenu();
            int orderMenuCount = menu.getOrderMenuCount();
            totalOrderAmount+=christmasMenu.getMenuPrice(menuName)*orderMenuCount;
        }
        order.updateTotalOrderAmount(totalOrderAmount);
    }
}
