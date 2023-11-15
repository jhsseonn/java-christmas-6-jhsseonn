package christmas.controller;

import christmas.consts.ChristmasConsts;
import christmas.consts.DecemberEventBadge;
import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.exception.GlobalExceptionHandler;
import christmas.service.ChristmasService;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

import java.util.HashMap;

public class ChristmasController implements ChristmasConsts {

    final ChristmasInputView christmasInputView = new ChristmasInputView();
    final ChristmasOutputView christmasOutputView = new ChristmasOutputView();
    final ChristmasService christmasService = new ChristmasService();
    final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    public int getValidVisitDay(){
        int expectedVisitDay = 0;
        try {
            String inputVisitDay = christmasInputView.getExpectedVisitDay();
            expectedVisitDay = Integer.parseInt(inputVisitDay);
        } catch (NumberFormatException e) {
            System.out.printf("%s %s", ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getValidVisitDay();
        } finally {
            return expectedVisitDay;
        }
    }

    public void isValidRangeVisitDay(int inputDay){
        try{
            if (inputDay<1 || inputDay>31){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.printf("%s %s", ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getValidVisitDay();
        }
    }

    public void runPromotion(){
        // 12월 이벤트 플래너 소개 문구 출력
        christmasOutputView.printWelcome();
        // 예상 방문 날짜 입력받기
        int expectedVisitDay = getValidVisitDay();
        isValidRangeVisitDay(expectedVisitDay);

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
        if (christmasPromotion.getPromotionResult().containsKey("증정 이벤트")){
            christmasOutputView.printPresentationEventMenu();
        }
        // 혜택 내역 및 총 혜택 금액 출력하기
        if (christmasPromotion.getTotalPromotionAmount()!=0){
            christmasOutputView.printPromotionResult(christmasPromotion.getPromotionResult());
            christmasOutputView.printTotalPromotionAmount(christmasPromotion.getTotalPromotionAmount());
        } else if (christmasPromotion.getTotalPromotionAmount()==0){
            christmasOutputView.printPromotionResultNone();
            christmasOutputView.printTotalPromotionAmountNone();
        }
        // 할인 후 예상 결제 금액 출력하기
        int expectAmountAfterDiscount = christmasService.getExpectAmountAfterDiscount(christmasPromotion);
        christmasOutputView.printExpectAmountAfterDiscount(expectAmountAfterDiscount);
        // 12월 이벤트 배지 출력하기
        DecemberEventBadge decemberEventBadge = christmasPromotion.getDecemberEventBadge();
        christmasOutputView.printDecemberEventBadge(decemberEventBadge.getDecemberEventBadge());
    }
}
