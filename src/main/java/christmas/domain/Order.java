package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private DayOfWeek orderDayOrWeek;
    private List<OrderMenu> orderMenus;

    private Order(LocalDate orderDate, DayOfWeek orderDayOrWeek, List<OrderMenu> orderMenus){
        this.orderDate = orderDate;
        this.orderDayOrWeek = orderDayOrWeek;
        this.orderMenus = orderMenus;
    }
}
