package christmas.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasPromotionTest {

    @Test
    void 크리스마스_디데이_이벤트_추가() {
        Order orderHistory = new Order(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(orderHistory);
        christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(1200);
    }
}