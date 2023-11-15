package christmas;

import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.service.ChristmasService;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

import java.util.HashMap;

public class Application {
    public static void main(String[] args) {

        final ChristmasInputView christmasInputView = new ChristmasInputView();
        final ChristmasOutputView christmasOutputView = new ChristmasOutputView();
        final ChristmasService christmasService = new ChristmasService();

        // 12월 이벤트 플래너 소개 문구 출력
        christmasOutputView.printWelcome();
        // 예상 방문 날짜 입력받기
        int expectedVisitDay = christmasInputView.getExpectedVisitDay();
        // 주문할 메뉴와 메뉴 개수 입력받기
        String orderMenus = christmasInputView.getOrderMenus();
        // 입력한 날짜, 메뉴 정보로 order, christmasPromotion 객체 생성하기
        Order order = new Order(expectedVisitDay, orderMenus);
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(order);
        // 총 주문 메뉴 알려주기
        HashMap<String, Integer> orderMenuPreview = christmasService.getTotalOrderMenu(order);
        christmasOutputView.printOrderMenus(orderMenuPreview);
        // 총 주문 금액 알려주기
        order.computeTotalOrderAmount();
        christmasOutputView.printTotalOrderAmount(order.getTotalOrderAmount());
        // 이벤트 계산하기
        christmasService.confirmEvents(christmasPromotion);
        // 증정 메뉴 출력하기

    }
}
