package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    public Order order;

    @BeforeEach
    void 유효한_order_생성(){
        final OrderMenu steak = new OrderMenu("티본스테이크", 1);
        final OrderMenu barbequeRip = new OrderMenu("바비큐립", 1);
        final OrderMenu chocolateCake = new OrderMenu("초코케이크", 2);
        final OrderMenu zeroCoke = new OrderMenu("제로콜라", 1);
        order = new Order(3, List.of(steak, barbequeRip, chocolateCake, zeroCoke));
    }

    @Test
    void 총_주문_금액_계산하기() {
        order.computeTotalOrderAmount();
        int result = order.getTotalOrderAmount();
        assertThat(result).isEqualTo(142000);
    }
}