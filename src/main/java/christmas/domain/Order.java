package christmas.domain;

import christmas.consts.ChristmasConsts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Order implements ChristmasConsts {
    private LocalDate orderDate;
    private DayOfWeek orderDayOfWeek;
    private List<OrderMenu> orderMenus;

    private Order(int expectedVisitDay, List<OrderMenu> orderMenus){
        LocalDate orderDate = updateOrderDate(expectedVisitDay);
        DayOfWeek orderDayOfWeek = updateOrderDayOfWeek(orderDate);
        this.orderDate = orderDate;
        this.orderDayOfWeek = orderDayOfWeek;
        this.orderMenus = orderMenus;
    }

    public LocalDate updateOrderDate(int expectedVisitDay){
        LocalDate date = LocalDate.of(DECEMBER_EVENT_YEAR, DECEMBER_EVENT_MONTH, expectedVisitDay);
        return date;
    }

    public DayOfWeek updateOrderDayOfWeek(LocalDate orderDate){
        DayOfWeek orderDayOrWeek = orderDate.getDayOfWeek();
        return orderDayOrWeek;
    }
}
