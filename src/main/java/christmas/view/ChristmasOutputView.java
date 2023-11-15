package christmas.view;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasPromotionEvents;
import christmas.consts.DecemberEventBadge;

import javax.sound.midi.SysexMessage;
import java.util.HashMap;

public class ChristmasOutputView implements ChristmasConsts {
    public void printWelcome(){
        System.out.println(DECEMBER_EVENT_WELCOME);
    }

    public void printChristmasPromotionPreview(int orderDay){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", orderDay);
    }

    public void printOrderMenus(HashMap<String, Integer> orderMenuPreview){
        System.out.println("<주문 메뉴>");
        orderMenuPreview.forEach((key, value) -> {
            System.out.printf("%s %d개\n", key, value);
        });
        System.out.println();
    }

    public void printTotalOrderAmount(int totalOrderAmount){
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%d원\n", totalOrderAmount);
        System.out.println();
    }

    public void printPresentationEventMenu(){
        System.out.println("<증정 메뉴>");
        System.out.printf("%s %d개\n", "샴페인", 1);
        System.out.println();
    }

    public void printPromotionResult(HashMap<ChristmasPromotionEvents, Integer> promotionResult){
        System.out.println("<혜택 내역>");
        promotionResult.forEach((key, value) -> {
            System.out.printf("%s: -%d원\n", key.getPromotionName(), value);
        });
        System.out.println();
    }

    public void printPromotionResultNone(){
        System.out.println("<혜택 내역>");
        System.out.println("없음");
        System.out.println();
    }

    public void printTotalPromotionAmount(int totalPromotionAmount){
        System.out.println("<총혜택 금액>");
        System.out.printf("-%d\n", totalPromotionAmount);
        System.out.println();
    }

    public void printTotalPromotionAmountNone(){
        System.out.println("<총혜택 금액>");
        System.out.println("없음");
        System.out.println();
    }

    public void printExpectAmountAfterDiscount(int expectAmountAfterDiscount){
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%d\n", expectAmountAfterDiscount);
        System.out.println();
    }

    public void printDecemberEventBadge(String decemberEventBadge){
        System.out.println("<12월 이벤트 배지>");
        System.out.println(decemberEventBadge);
    }
}
