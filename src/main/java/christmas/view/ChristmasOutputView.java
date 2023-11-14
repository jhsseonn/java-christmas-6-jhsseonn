package christmas.view;

import christmas.consts.ChristmasConsts;

import java.util.HashMap;

public class ChristmasOutputView implements ChristmasConsts {
    public void printWelcome(){
        System.out.println(DECEMBER_EVENT_WELCOME);
    }

    public void printOrderMenus(HashMap<String, Integer> orderMenuPreview){
        System.out.println("<주문 메뉴>");
        orderMenuPreview.forEach((key, value) -> {
            System.out.printf("%s %d개", key, value);
        });
    }
}
