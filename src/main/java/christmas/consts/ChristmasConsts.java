package christmas.consts;

import java.util.HashMap;
import java.util.HashSet;

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
    int DECEMBER_EVENT_BADGE_STAR_AMOUNT = 5000;
    int DECEMBER_EVENT_BADGE_TREE_AMOUNT = 10000;
    int DECEMBER_EVENT_BADGE_SANTA_AMOUNT = 20000;
    int DECEMBER_EVENT_BADGE_NONE_AMOUNT = 0;
    int DECEMBER_EVENT_YEAR = 2023;
    int DECEMBER_EVENT_MONTH= 12;

    String DECEMBER_EVENT_WELCOME = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    String DECEMBER_EVENT_EXPECTED_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
}
