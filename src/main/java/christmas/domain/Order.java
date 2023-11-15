package christmas.domain;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Order implements ChristmasConsts {
    private LocalDate orderDate;
    private DayOfWeek orderDayOfWeek;
    private List<OrderMenu> orderMenus;
    private int totalOrderAmount;

    public Order(int expectedVisitDay, List<OrderMenu> orderMenus){
        LocalDate orderDate = updateOrderDate(expectedVisitDay);
        DayOfWeek orderDayOfWeek = updateOrderDayOfWeek(orderDate);
        this.orderDate = orderDate;
        this.orderDayOfWeek = orderDayOfWeek;
        updateOrderMenus(orderMenus);
        this.totalOrderAmount = INTEGER_RESET;
    }

    public LocalDate updateOrderDate(int expectedVisitDay){
        LocalDate date = LocalDate.of(DECEMBER_EVENT_YEAR, DECEMBER_EVENT_MONTH, expectedVisitDay);
        return date;
    }

    public DayOfWeek updateOrderDayOfWeek(LocalDate orderDate){
        DayOfWeek orderDayOrWeek = orderDate.getDayOfWeek();
        return orderDayOrWeek;
    }

    public void updateOrderMenus(List<OrderMenu> orderMenus){
        this.orderMenus = orderMenus;
    }

    public List<OrderMenu> getOrderMenus(){
        return this.orderMenus;
    }

    public void updateTotalOrderAmount(int totalOrderAmount){
        this.totalOrderAmount = totalOrderAmount;
    }

    public int getTotalOrderAmount(){
        return totalOrderAmount;
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    public DayOfWeek getOrderDayOfWeek(){
        return orderDayOfWeek;
    }

    public void computeTotalOrderAmount() {
        List<OrderMenu> orderMenus = getOrderMenus();
        int totalOrderAmount = INTEGER_RESET;
        for (OrderMenu menu : orderMenus) {
            ChristmasMenu christmasMenu = menu.getChristmasMenu();
            String menuName = menu.getMenu();
            int orderMenuCount = menu.getOrderMenuCount();
            totalOrderAmount += christmasMenu.getMenuPrice(menuName) * orderMenuCount;
        }
        updateTotalOrderAmount(totalOrderAmount);
    }
}
