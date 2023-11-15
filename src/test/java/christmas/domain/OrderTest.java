package christmas.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    public Order order = new Order(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

    @Test
    void 총_주문_금액_계산하기() {
        order.computeTotalOrderAmount();
        int result = order.getTotalOrderAmount();
        assertThat(result).isEqualTo(142000);
    }
}