package christmas.consts;

public enum ChristmasPromotionEvents {
    CHRISTMAS_DDAY_PROMOTION("크리스마스 디데이 할인"),
    WEEKDAY_PROMOTION("평일 할인"),
    WEEKEND_PROMOTION("주말 할인"),
    SPECIAL_PROMOTION("특별 할인"),
    PRESENTATION_PROMOTION("증정 이벤트");

    private String promotionName;

    ChristmasPromotionEvents(String promotionName){
        this.promotionName = promotionName;
    }

    public String getPromotionName(){
        return promotionName;
    }
}
