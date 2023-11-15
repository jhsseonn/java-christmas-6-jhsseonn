package christmas.view;

import christmas.consts.ChristmasConsts;
import christmas.consts.DecemberEventBadge;

import javax.sound.midi.SysexMessage;
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

    public void printTotalOrderAmount(int totalOrderAmount){
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%d원", totalOrderAmount);
    }

    public void printPresentationEventMenu(){
        System.out.println("<증정 메뉴>");
        System.out.printf("%s %d개", "샴페인", 1);
    }

    public void printPromotionResult(HashMap<String, Integer> promotionResult){
        promotionResult.forEach((key, value) -> {
            System.out.printf("%s: -%d원", key, value);
        });
    }

    public void printTotalPromotionAmount(int totalPromotionAmount){
        System.out.println("<총혜택 금액>");
        System.out.printf("-%d", totalPromotionAmount);
    }

    public void printExpectAmountAfterDiscount(int expectAmountAfterDiscount){
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%d", expectAmountAfterDiscount);
    }

    public void printDecemberEventBadge(String decemberEventBadge){
        System.out.println("<12월 이벤트 배지>");
        System.out.println(decemberEventBadge);
    }
}
