package christmas;

import christmas.consts.ChristmasConsts;
import christmas.controller.ChristmasController;

public class Application implements ChristmasConsts {
    public static void main(String[] args) {

        final ChristmasController christmasController = new ChristmasController();
        christmasController.runPromotion();
    }
}
