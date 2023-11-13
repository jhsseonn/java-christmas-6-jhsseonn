package christmas.domain;

import christmas.consts.ChristmasConsts;

import java.util.HashMap;

public enum ChristmasMenu implements ChristmasConsts {
    APPETIZER(APPETIZERS),
    MAIN(MAINS),
    DESSERT(DESSERTS),
    BEVERAGE(BEVERAGES);

    private HashMap<String, Integer> menu;

    ChristmasMenu(HashMap<String, Integer> menu){
        this.menu = menu;
    }
}
