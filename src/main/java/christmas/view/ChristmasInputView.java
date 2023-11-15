package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.consts.ChristmasConsts;
import christmas.exception.GlobalExceptionHandler;

public class ChristmasInputView implements ChristmasConsts {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    // inputView가 아닌 service나 order 객체 생성시 유효성 검증으로 리팩토링 필요
    public int getExpectedVisitDay(){
        System.out.println(DECEMBER_EVENT_EXPECTED_VISIT_DAY);
        globalExceptionHandler.isInputInteger(Console.readLine(), "유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
    }

    public String getOrderMenus(){
        System.out.println(DECEMBER_EVENT_ORDER_MENU);
        return Console.readLine();
    }
}
