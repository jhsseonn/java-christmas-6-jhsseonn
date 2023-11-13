package christmas.domain;

public class OrderMenu {
    private ChristmasMenu christmasMenu;
    private String menu;
    private int orderMenuCount;

    public OrderMenu(String menu, int orderMenuCount){
        ChristmasMenu christmasMenu = ChristmasMenu.findByMenu(menu);
        this.christmasMenu = christmasMenu;
        this.menu = menu;
        this.orderMenuCount = orderMenuCount;
    }


}
