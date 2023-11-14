package christmas.domain;

import christmas.consts.ChristmasMenu;

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

    public ChristmasMenu getChristmasMenu(){
        return this.christmasMenu;
    }

    public String getMenu(){
        return this.menu;
    }

    public int getOrderMenuCount(){
        return this.orderMenuCount;
    }
}
