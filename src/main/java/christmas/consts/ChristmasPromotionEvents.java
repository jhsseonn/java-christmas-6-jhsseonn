package christmas.consts;

public enum ChristmasPromotionEvents implements ChristmasConsts{
    CHRISTMAS_DDAY_PROMOTION(PROMOTION_NAME_CHRISTMAS_DDAY),
    WEEKDAY_PROMOTION(PROMOTION_NAME_WEEKDAY),
    WEEKEND_PROMOTION(PROMOTION_NAME_WEEKEND),
    SPECIAL_PROMOTION(PROMOTION_NAME_SPECIAL),
    PRESENTATION_PROMOTION(PROMOTION_NAME_PRESENTATION);

    private String promotionName;

    ChristmasPromotionEvents(String promotionName){
        this.promotionName = promotionName;
    }

    public String getPromotionName(){
        return promotionName;
    }
}
