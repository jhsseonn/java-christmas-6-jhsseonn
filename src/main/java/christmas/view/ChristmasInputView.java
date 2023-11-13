package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.consts.ChristmasConsts;

public class ChristmasInputView implements ChristmasConsts {
    public int getExpectedVisitDay(){
        System.out.println(DECEMBER_EVENT_EXPECTED_VISIT_DAY);
        return Integer.parseInt(Console.readLine());
    }
}
