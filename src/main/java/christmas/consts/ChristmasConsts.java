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

}
