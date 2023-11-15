package christmas.controller;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;
import christmas.consts.ChristmasPromotionEvents;
import christmas.consts.DecemberEventBadge;
import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.service.ChristmasService;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChristmasController implements ChristmasConsts {

    final ChristmasInputView christmasInputView = new ChristmasInputView();
    final ChristmasOutputView christmasOutputView = new ChristmasOutputView();
    final ChristmasService christmasService = new ChristmasService();

    public int getValidVisitDay(){
        int expectedVisitDay = INTEGER_RESET;
        try {
            String inputVisitDay = christmasInputView.getExpectedVisitDay();
            expectedVisitDay = Integer.parseInt(inputVisitDay);
        } catch (NumberFormatException e) {
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getValidVisitDay();
        } finally {
            return expectedVisitDay;
        }
    }

    public void isValidRangeVisitDay(int inputDay){
        try{
            if (inputDay<EVENT_START_DAY || inputDay>EVENT_END_DAY){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getValidVisitDay();
        }
    }

    public List<OrderMenu> getOrderMenus(){
        String inputOrderMenus = christmasInputView.getOrderMenus();
        List<OrderMenu> createOrderMenus = new ArrayList<>();
        List<String> orderMenus = List.of(inputOrderMenus.split(SPLIT_INPUT_STRING));
        int totalOrderMenuCount = INTEGER_RESET;
        for(String menu:orderMenus){
            isOrderMenuAlreadyExist(createOrderMenus, menu);
            OrderMenu orderMenu = getOrderMenu(menu);
            createOrderMenus.add(orderMenu);
            totalOrderMenuCount+=orderMenu.getOrderMenuCount();
        }
        isValidRangeOfMenuCount(totalOrderMenuCount);
        return createOrderMenus;
    }

    public OrderMenu getOrderMenu(String menu){
        isValidMenuFormat(menu);
        List<String> menuCount = List.of(menu.split(SPLIT_INPUT_MENU));
        String menuName = menuCount.get(0);
        isMenuNameExist(menuName);
        int orderMenuCount = getValidMenuCount(menuCount.get(1));
        isValidRangeOfMenuCount(orderMenuCount);
        final OrderMenu orderMenu = new OrderMenu(menuName, orderMenuCount);
        return orderMenu;
    }

    public void isValidMenuFormat(String menu){
        try{
            if(!menu.contains(SPLIT_INPUT_MENU)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        }
    }

    public void isMenuNameExist(String menuName){
        try{
            if(ChristmasMenu.findByMenu(menuName)==ChristmasMenu.NONE){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        }
    }

    public void isOrderMenuAlreadyExist(List<OrderMenu> orderMenus, String menu){
        List<String> orderMenuNames = new ArrayList<>();
        for(OrderMenu orderMenu:orderMenus){
           orderMenuNames.add(orderMenu.getMenu());
        }
        try {
            if (orderMenuNames.contains(menu)){
                throw new IllegalArgumentException();
            }
        } catch(IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        }
    }

    public int getValidMenuCount(String menuAmount){
        int menuCount = INTEGER_RESET;
        try {
            menuCount = Integer.parseInt(menuAmount);
        } catch (NumberFormatException e) {
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        } finally {
            return menuCount;
        }
    }

    public void isValidRangeOfMenuCount(int menuCount){
        try {
            if (menuCount>MENU_COUNT_MAXIMUM){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        }
    }

    public int getExpectVisitDate(){
        int expectedVisitDay = getValidVisitDay();
        isValidRangeVisitDay(expectedVisitDay);
        return expectedVisitDay;
    }

    public void printOrderMenus(Order order){
        HashMap<String, Integer> orderMenuPreview = christmasService.getTotalOrderMenu(order);
        christmasOutputView.printOrderMenus(orderMenuPreview);
    }

    public void printTotalOrderAmount(Order order){
        order.computeTotalOrderAmount();
        christmasOutputView.printTotalOrderAmount(order.getTotalOrderAmount());
    }

    public void printPresentationEventMenu(ChristmasPromotion christmasPromotion){
        if (christmasPromotion.getPromotionResult().containsKey(ChristmasPromotionEvents.PRESENTATION_PROMOTION)){
            christmasOutputView.printPresentationEventMenu();
        }
    }

    public void printPromotionsAndAmount(ChristmasPromotion christmasPromotion){
        if (christmasPromotion.getTotalPromotionAmount()!=AMOUNT_IS_ZERO){
            christmasOutputView.printPromotionResult(christmasPromotion.getPromotionResult());
            christmasOutputView.printTotalPromotionAmount(christmasPromotion.getTotalPromotionAmount());
        } else if (christmasPromotion.getTotalPromotionAmount()==AMOUNT_IS_ZERO){
            christmasOutputView.printPromotionResultNone();
            christmasOutputView.printTotalPromotionAmountNone();
        }
    }

    public void printDecemberEventPlanner(Order order, ChristmasPromotion christmasPromotion){
        printOrderMenus(order);
        printTotalOrderAmount(order);

        christmasService.confirmEvents(christmasPromotion);

        printPresentationEventMenu(christmasPromotion);
        printPromotionsAndAmount(christmasPromotion);
    }

    public void printExpectAmountAfterDiscount(ChristmasPromotion christmasPromotion){
        int expectAmountAfterDiscount = christmasService.getExpectAmountAfterDiscount(christmasPromotion);
        christmasOutputView.printExpectAmountAfterDiscount(expectAmountAfterDiscount);
    }

    public void printDecemberEventBadge(ChristmasPromotion christmasPromotion){
        DecemberEventBadge decemberEventBadge = christmasPromotion.getDecemberEventBadge();
        christmasOutputView.printDecemberEventBadge(decemberEventBadge.getDecemberEventBadge());
    }

    public void runPromotion(){
        christmasOutputView.printWelcome();
        int expectedVisitDay = getExpectVisitDate();
        List<OrderMenu> orderMenus = getOrderMenus();
        Order order = new Order(expectedVisitDay, orderMenus);
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(order);
        christmasOutputView.printChristmasPromotionPreview(expectedVisitDay);

        printDecemberEventPlanner(order, christmasPromotion);
        printExpectAmountAfterDiscount(christmasPromotion);
        printDecemberEventBadge(christmasPromotion);
    }
}
