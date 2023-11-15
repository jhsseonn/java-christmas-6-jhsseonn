package christmas.domain;

import christmas.consts.DecemberEventBadge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasPromotionTest {

    public Order orderHistory;
    public ChristmasPromotion christmasPromotion;
    public List<OrderMenu> orderMenus;


    @BeforeEach
    void 유효한_order_생성(){
        final OrderMenu steak = new OrderMenu("티본스테이크", 1);
        final OrderMenu barbequeRip = new OrderMenu("바비큐립", 1);
        final OrderMenu chocolateCake = new OrderMenu("초코케이크", 2);
        final OrderMenu zeroCoke = new OrderMenu("제로콜라", 1);
        orderHistory = new Order(3, List.of(steak, barbequeRip, chocolateCake, zeroCoke));
        christmasPromotion = new ChristmasPromotion(orderHistory);
    }
    @Test
    void 크리스마스_디데이_이벤트_추가() {
        christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(1200);
    }

    @Test
    void 평일_할인_이벤트_추가(){
        christmasPromotion.addWeekDayEvent(orderHistory);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(4046);
    }

    @Test
    void 주말_할인_이벤트_추가(){
        christmasPromotion.addWeekEndEvent(orderHistory);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(4046);
    }

    @Test
    void 특별_할인_이벤트_추가(){
        christmasPromotion.addSpecialEvent(orderHistory);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(1000);
    }

    @Test
    void 증정_이벤트_추가(){
        christmasPromotion.addPresentationEvent(orderHistory);
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(25000);
    }

    @Test
    void 총_혜택_금액으로_배지_부여하기(){
        christmasPromotion.addChristmasDDayEvent(orderHistory.getOrderDate());
        christmasPromotion.addWeekDayEvent(orderHistory);
        christmasPromotion.addWeekEndEvent(orderHistory);
        christmasPromotion.addSpecialEvent(orderHistory);
        christmasPromotion.addPresentationEvent(orderHistory);
        christmasPromotion.updateDecemberEventBadge();
        DecemberEventBadge result = christmasPromotion.getDecemberEventBadge();
        assertThat(christmasPromotion.getTotalPromotionAmount()).isEqualTo(31246);
        assertThat(result).isEqualTo(DecemberEventBadge.SANTA);
    }
}