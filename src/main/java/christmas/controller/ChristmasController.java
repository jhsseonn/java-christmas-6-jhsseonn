package christmas.controller;

import christmas.consts.ChristmasConsts;
import christmas.consts.ChristmasMenu;
import christmas.consts.ChristmasPromotionEvents;
import christmas.consts.DecemberEventBadge;
import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.service.ChristmasService;
import christmas.util.ChristmasUtil;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ChristmasController implements ChristmasConsts {

    final ChristmasInputView christmasInputView = new ChristmasInputView();
    final ChristmasOutputView christmasOutputView = new ChristmasOutputView();
    final ChristmasService christmasService = new ChristmasService();
    final ChristmasUtil christmasUtil = new ChristmasUtil();

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

    public List<OrderMenu> getOrderMenus(){
        List<OrderMenu> createOrderMenus = new ArrayList<>();
        String inputOrderMenus = christmasInputView.getOrderMenus();
        List<String> orderMenus = christmasService.getInputOrderMenus(inputOrderMenus);
        try{
            for(String menu:orderMenus){
                OrderMenu orderMenu = getOrderMenu(menu);
                isOrderMenuAlreadyExist(createOrderMenus, orderMenu.getMenu());
                createOrderMenus.add(orderMenu);
            }
            validateOrderMenus(createOrderMenus);
        } catch(IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_MENU_COUNT);
            getOrderMenus();
        }
        return createOrderMenus;
    }

    public OrderMenu getOrderMenu(String menu) throws IllegalArgumentException{
        isValidMenuFormat(menu);
        List<String> menuCount = List.of(menu.split(SPLIT_INPUT_MENU));
        String menuName = menuCount.get(0);
        isMenuNameExist(menuName);
        int orderMenuCount = getValidMenuCount(menuCount.get(1));
        isValidRangeOfMenuCount(orderMenuCount);
        final OrderMenu orderMenu = new OrderMenu(menuName, orderMenuCount);
        return orderMenu;
    }

    public int getExpectVisitDate(){
        String inputVisitDay = christmasInputView.getExpectedVisitDay();
        int expectedVisitDay = INTEGER_RESET;
        try{
            expectedVisitDay = christmasService.getExpectedVisitDay(inputVisitDay);
        } catch(NumberFormatException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getExpectVisitDate();
        } catch(IllegalArgumentException e){
            System.out.printf(ERROR_MESSAGE_FORMAT, ERROR_MESSAGE_HEADER, ILLEGAL_INPUT_DAY);
            getExpectVisitDate();
        }
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

    public void isValidMenuFormat(String menu){
        if(!menu.contains(SPLIT_INPUT_MENU)){
            throw new IllegalArgumentException();
        }
    }

    public void isMenuNameExist(String menuName){
        if(ChristmasMenu.findByMenu(menuName)==ChristmasMenu.NONE){
            throw new IllegalArgumentException();
        }
    }

    public void isOrderMenuAlreadyExist(List<OrderMenu> orderMenus, String menu) throws IllegalArgumentException{
        List<String> orderMenuNames = new ArrayList<>();
        for(OrderMenu orderMenu:orderMenus){
            orderMenuNames.add(orderMenu.getMenu());
        }
        if (orderMenuNames.contains(menu)){
            throw new IllegalArgumentException();
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

    public void isValidRangeOfMenuCount(int menuCount) throws IllegalArgumentException{
        if (menuCount>MENU_COUNT_MAXIMUM){
            throw new IllegalArgumentException();
        }
    }

    public void isOrderMenusAllBeverage(List<OrderMenu> orderMenus) throws IllegalArgumentException{
        HashSet<ChristmasMenu> christmasMenus = new HashSet<>();
        for(OrderMenu orderMenu:orderMenus){
            christmasMenus.add(orderMenu.getChristmasMenu());
        }
        if (christmasMenus.size()==ORDER_MENUS_ARE_ONLY_BEVERAGE && christmasMenus.contains(ChristmasMenu.BEVERAGE)){
            throw new IllegalArgumentException();
        }
    }

    public void validateMenuCount(List<OrderMenu> orderMenus) throws IllegalArgumentException{
        int totalOrderAmount = INTEGER_RESET;
        for(OrderMenu orderMenu:orderMenus){
            totalOrderAmount+=orderMenu.getOrderMenuCount();
        }
        isValidRangeOfMenuCount(totalOrderAmount);
    }

    public void validateOrderMenus(List<OrderMenu> orderMenus){
        validateMenuCount(orderMenus);
        isOrderMenusAllBeverage(orderMenus);
    }
}
