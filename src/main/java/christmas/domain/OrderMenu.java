package christmas.domain;

public class OrderMenu {
    private ChristmasMenu christmasMenu;
    private int orderMenuCount;

    private OrderMenu(ChristmasMenu christmasMenu, int orderMenuCount){
        this.christmasMenu = christmasMenu;
        this.orderMenuCount = orderMenuCount;
    }
}
