package christmas.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChristmasPromotion {
    private Order orderHistory;
    private List<HashMap<String, Integer>> promotionResult;
    private int totalPromotionAmount;

    public ChristmasPromotion(Order orderHistory){
        this.orderHistory = orderHistory;
        this.promotionResult = new ArrayList<>();
        this.totalPromotionAmount = 0;
    }
}
