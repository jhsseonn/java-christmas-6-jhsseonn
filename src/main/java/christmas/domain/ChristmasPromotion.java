package christmas.domain;

import java.util.HashMap;
import java.util.List;

public class ChristmasPromotion {
    private Order orderHistory;
    private List<HashMap<String, Integer>> promotionResult;
    private int totalPromotionAmount;

    private ChristmasPromotion(
            Order orderHistory,
            List<HashMap<String, Integer>> promotionResult,
            int totalPromotionAmount
    ){
        this.orderHistory = orderHistory;
        this.promotionResult = promotionResult;
        this.totalPromotionAmount = totalPromotionAmount;
    }
}
