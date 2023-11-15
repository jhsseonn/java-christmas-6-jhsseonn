package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.consts.ChristmasConsts;
import christmas.exception.GlobalExceptionHandler;

public class ChristmasInputView implements ChristmasConsts {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    public String getExpectedVisitDay(){
        System.out.println(DECEMBER_EVENT_EXPECTED_VISIT_DAY);
        return Console.readLine();
    }

    public String getOrderMenus(){
        System.out.println(DECEMBER_EVENT_ORDER_MENU);
        return Console.readLine();
    }
}
