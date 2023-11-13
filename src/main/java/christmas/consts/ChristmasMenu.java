package christmas.consts;

import java.util.Arrays;
import java.util.HashMap;

public enum ChristmasMenu implements ChristmasConsts {
    APPETIZER(APPETIZERS),
    MAIN(MAINS),
    DESSERT(DESSERTS),
    BEVERAGE(BEVERAGES),
    NONE(new HashMap<>());

    private HashMap<String, Integer> menu;

    ChristmasMenu(HashMap<String, Integer> menu){
        this.menu = menu;
    }

    public static ChristmasMenu findByMenu(String menu){
        return Arrays.stream(ChristmasMenu.values())
                .filter(christmasMenu -> christmasMenu.menu.containsKey(menu))
                .findAny()
                .orElse(NONE);
    }
}
