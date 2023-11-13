package christmas.domain;

import christmas.consts.ChristmasConsts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements ChristmasConsts {
    private LocalDate orderDate;
    private DayOfWeek orderDayOfWeek;
    private List<OrderMenu> orderMenus;

    public Order(int expectedVisitDay, String orderMenus){
        LocalDate orderDate = updateOrderDate(expectedVisitDay);
        DayOfWeek orderDayOfWeek = updateOrderDayOfWeek(orderDate);
        this.orderDate = orderDate;
        this.orderDayOfWeek = orderDayOfWeek;
        this.orderMenus = updateOrderMenus(orderMenus);
    }

    public LocalDate updateOrderDate(int expectedVisitDay){
        LocalDate date = LocalDate.of(DECEMBER_EVENT_YEAR, DECEMBER_EVENT_MONTH, expectedVisitDay);
        return date;
    }

    public DayOfWeek updateOrderDayOfWeek(LocalDate orderDate){
        DayOfWeek orderDayOrWeek = orderDate.getDayOfWeek();
        return orderDayOrWeek;
    }

    public List<OrderMenu> updateOrderMenus(String inputOrderMenus){
        List<OrderMenu> createOrderMenus = new ArrayList<>();
        List<String> orderMenus = List.of(inputOrderMenus.split(","));
        for(String menu:orderMenus){
            List<String> menuCount = List.of(menu.split("-"));
            String menuName = menuCount.get(0);
            int menuAmount = Integer.parseInt(menuCount.get(1));
            final OrderMenu orderMenu = new OrderMenu(menuName, menuAmount);
            createOrderMenus.add(orderMenu);
        }
        return createOrderMenus;
    }
}
