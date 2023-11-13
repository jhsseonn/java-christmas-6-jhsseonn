package christmas;

import christmas.domain.ChristmasPromotion;
import christmas.domain.Order;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class Application {
    public static void main(String[] args) {

        final ChristmasInputView christmasInputView = new ChristmasInputView();
        final ChristmasOutputView christmasOutputView = new ChristmasOutputView();

        christmasOutputView.printWelcome();
        int expectedVisitDay = christmasInputView.getExpectedVisitDay();
        String orderMenus = christmasInputView.getOrderMenus();

        Order order = new Order(expectedVisitDay, orderMenus);
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(order);

    }
}
