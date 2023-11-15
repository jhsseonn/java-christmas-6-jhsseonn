package christmas.domain;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements ChristmasConsts {
    private LocalDate orderDate;
    private DayOfWeek orderDayOfWeek;
    private List<OrderMenu> orderMenus;
    private int totalOrderAmount;

    public Order(int expectedVisitDay, String orderMenus){
        LocalDate orderDate = updateOrderDate(expectedVisitDay);
        DayOfWeek orderDayOfWeek = updateOrderDayOfWeek(orderDate);
        this.orderDate = orderDate;
        this.orderDayOfWeek = orderDayOfWeek;
        this.orderMenus = updateOrderMenus(orderMenus);
        this.totalOrderAmount = 0;
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

    public void computeTotalOrderAmount(){
        List<OrderMenu> orderMenus = getOrderMenus();
        int totalOrderAmount=0;
        for(OrderMenu menu:orderMenus){
            ChristmasMenu christmasMenu = menu.getChristmasMenu();
            String menuName = menu.getMenu();
            int orderMenuCount = menu.getOrderMenuCount();
            totalOrderAmount+=christmasMenu.getMenuPrice(menuName)*orderMenuCount;
        }
        updateTotalOrderAmount(totalOrderAmount);
    }
}
