package christmas.discount.domain;

import christmas.basket.domain.Basket;
import christmas.menu.domain.TestMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DiscountCalculator 클래스는")
class DiscountCalculatorTest {
    @DisplayName("할인 정책을 주입받아, 주입받은 정책들로 총할인 금액을 반환한다")
    @Test
    void discount() {
        // given
        DiscountCalculator calculator = new DiscountCalculator(
                getDiscountPolicyByDiscountPrice(1000),
                getDiscountPolicyByDiscountPrice(500)
        );

        Basket basket = new Basket(List.of(
                new TestMenu(10000),
                new TestMenu(20000),
                new TestMenu(30000)
        ));

        // when
        int totalDiscountPrice = calculator.calculate(basket);

        // then
        assertThat(totalDiscountPrice).isEqualTo(1500);
    }

    private DiscountPolicy getDiscountPolicyByDiscountPrice(int discountPrice) {
        return new DiscountPolicy() {
            @Override
            protected int calculateDiscountPrice(Basket basket) {
                return discountPrice;
            }
        };
    }
}
