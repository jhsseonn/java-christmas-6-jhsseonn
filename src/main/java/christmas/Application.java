package christmas;

import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class Application {
    public static void main(String[] args) {

        final ChristmasInputView christmasInputView = new ChristmasInputView();
        final ChristmasOutputView christmasOutputView = new ChristmasOutputView();

        christmasOutputView.printWelcome();
        int expectedVisitDay = christmasInputView.getExpectedVisitDay();

    }
}
