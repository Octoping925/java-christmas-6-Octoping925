package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DiscountPolicy 클래스는")
class DiscountPolicyTest {
    private static final DiscountPolicy discountPolicy = new DiscountPolicy("테스트 할인") {
        @Override
        public int calculateDiscountPrice(Basket basket) {
            return 1000;
        }
    };


    @DisplayName("최소 이벤트 금액 이상일 경우 할인이 적용된다.")
    @Test
    void discount() {
        // given
        Basket basket = new Basket(List.of(Menu.SEAFOOD_PASTA));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("최소 이벤트 금액 미만일 경우 할인이 적용되지 않는다.")
    @Test
    void discount_with_low_price() {
        // given
        Basket basket = new Basket(List.of(Menu.CAESAR_SALAD));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isZero();
    }
}