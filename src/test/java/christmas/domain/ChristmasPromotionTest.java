package christmas.domain;

import christmas.consts.DecemberEventBadge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasPromotionTest {

    public Order orderHistory;
    public ChristmasPromotion christmasPromotion;
    @BeforeEach
    void 유효한_order_생성(){
        orderHistory = new Order(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        christmasPromotion = new ChristmasPromotion(orderHistory);
    }
    @Test
    void 크리스마스_디데이_이벤트_추가() {
        int ddayPromotionAmount = christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        assertThat(ddayPromotionAmount).isEqualTo(1200);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(1200);
    }

    @Test
    void 평일_할인_이벤트_추가(){
        int weekdayEventAmount = christmasPromotion.addWeekDayEvent(orderHistory);
        assertThat(weekdayEventAmount).isEqualTo(4046);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(4046);
    }

    @Test
    void 주말_할인_이벤트_추가(){
        int weekendEventAmount = christmasPromotion.addWeekEndEvent(orderHistory);
        assertThat(weekendEventAmount).isEqualTo(4046);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(4046);
    }

    @Test
    void 특별_할인_이벤트_추가(){
        int specialEventAmount = christmasPromotion.addSpecialEvent(orderHistory);
        assertThat(specialEventAmount).isEqualTo(1000);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(1000);
    }

    @Test
    void 증정_이벤트_추가(){
        int presentationEventAmount = christmasPromotion.addPresentationEvent(orderHistory);
        assertThat(presentationEventAmount).isEqualTo(25000);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(25000);
    }

    @Test
    void 총_혜택_금액으로_배지_부여하기(){
        int totalPromotionAmount = christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        totalPromotionAmount+=christmasPromotion.addWeekDayEvent(orderHistory);
        totalPromotionAmount+=christmasPromotion.addWeekEndEvent(orderHistory);
        totalPromotionAmount+=christmasPromotion.addSpecialEvent(orderHistory);
        totalPromotionAmount+=christmasPromotion.addPresentationEvent(orderHistory);
        christmasPromotion.updateDecemberEventBadge();
        DecemberEventBadge result = christmasPromotion.getDecemberEventBadge();
        assertThat(totalPromotionAmount).isEqualTo(31246);
        assertThat(result).isEqualTo(DecemberEventBadge.SANTA);
    }
}