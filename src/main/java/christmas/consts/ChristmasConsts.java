package christmas.consts;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ChristmasConsts {
    HashMap<String, Integer> APPETIZERS = new HashMap<>(){{
        put("양송이수프",6000);
        put("타파스",5500);
        put("시저샐러드",8000);
    }};
    HashMap<String, Integer> MAINS = new HashMap<>(){{
        put("티본스테이크",55000);
        put("바비큐립",54000);
        put("해산물파스타",35000);
        put("크리스마스파스타",25000);
    }};
    HashMap<String, Integer> DESSERTS = new HashMap<>(){{
        put("초코케이크",15000);
        put("아이스크림",5000);
    }};
    HashMap<String, Integer> BEVERAGES = new HashMap<>(){{
        put("제로콜라",3000);
        put("레드와인",60000);
        put("샴페인",25000);
    }};
    List<DayOfWeek> WEEKDAYS = new ArrayList<>(
            List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)
    );
    List<DayOfWeek> WEEKENDS = new ArrayList<>(
            List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
    );
    int DECEMBER_EVENT_BADGE_STAR_AMOUNT = 5000;
    int DECEMBER_EVENT_BADGE_TREE_AMOUNT = 10000;
    int DECEMBER_EVENT_BADGE_SANTA_AMOUNT = 20000;
    int DECEMBER_EVENT_BADGE_NONE_AMOUNT = 0;
    int DECEMBER_EVENT_YEAR = 2023;
    int DECEMBER_EVENT_MONTH= 12;
    int INTEGER_RESET = 0;
    int EVENT_START_DAY = 1;
    int EVENT_END_DAY = 31;
    int AMOUNT_IS_ZERO = 0;
    int DDAY_PROMOTION_DAY_DISCOUNT = 100;
    int DDAY_PROMOTION_START_DISCOUNT = 1000;
    int WEEKDAY_AND_WEEKEND_DISCOUNT = 2023;
    int CHRISTMAS = 25;
    int SPECIAL_EVENT_DISCOUNT = 1000;
    int CHAMPAGNE_AMOUNT = 25000;
    int PRESENTATION_DISCOUNT_AMOUNT = 120000;
    int PRESENTATION_PROMOTION_COUNT = 1;
    int MENU_COUNT_MAXIMUM = 20;
    String SPLIT_INPUT_STRING = ",";
    String SPLIT_INPUT_MENU = "-";
    String DECIMAL_FORMAT_PATTERN = "###,###";
    String ORDER_MENUS_FORMAT = "%s %s개\n";
    String ORDER_AMOUNT_FORMAT = "%s원\n\n";
    String PRESENTATION_EVENT_FORMAT = "%s %d개\n\n";
    String PROMOTION_RESULT_FORMAT = "%s: -%s원\n";
    String TOTAL_PROMOTION_AMOUNT_FORMAT = "-%s원\n\n";
    String NONE_FORMAT = "없음\n";
    String PROMOTION_NAME_CHRISTMAS_DDAY = "크리스마스 디데이 할인";
    String PROMOTION_NAME_WEEKDAY = "평일 할인";
    String PROMOTION_NAME_WEEKEND = "주말 할인";
    String PROMOTION_NAME_SPECIAL = "특별 할인";
    String PROMOTION_NAME_PRESENTATION = "증정 이벤트";
    String BADGE_NAME_STAR = "별";
    String BADGE_NAME_TREE = "트리";
    String BADGE_NAME_SANTA = "산타";
    String BADGE_NAME_NONE = "없음";
    String PRESENTATION_PROMOTION_MENU = "샴페인";
    String DECEMBER_EVENT_WELCOME = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    String DECEMBER_EVENT_EXPECTED_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    String DECEMBER_EVENT_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    String CHRISTMAS_PROMOTION_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    String ORDER_MENU = "<주문 메뉴>";
    String TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    String PRESENTATION_MENU = "<증정 메뉴>";
    String PROMOTIONS = "<혜택 내역>";
    String TOTAL_PROMOTION_AMOUNT = "<총혜택 금액>";
    String EXPECT_AMOUNT_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    String DECEMBER_EVENT_BADGE = "<12월 이벤트 배지>";
    String ERROR_MESSAGE_HEADER = "[ERROR]";
    String ERROR_MESSAGE_FORMAT = "%s %s\n";
    String ILLEGAL_INPUT_DAY = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    String ILLEGAL_INPUT_MENU_COUNT = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
}
