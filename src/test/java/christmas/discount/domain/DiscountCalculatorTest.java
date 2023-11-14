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
        DiscountResult discountResult = calculator.calculate(basket);

        // then
        assertThat(discountResult.totalDiscountPrice()).isEqualTo(1500);

        assertThat(discountResult.discountHistories().get(0).discountName()).isEqualTo("테스트 할인 1000");
        assertThat(discountResult.discountHistories().get(0).discountPrice()).isEqualTo(1000);

        assertThat(discountResult.discountHistories().get(1).discountName()).isEqualTo("테스트 할인 500");
        assertThat(discountResult.discountHistories().get(1).discountPrice()).isEqualTo(500);
    }

    private DiscountPolicy getDiscountPolicyByDiscountPrice(int discountPrice) {
        return new DiscountPolicy("테스트 할인 " + discountPrice) {
            @Override
            protected int calculateDiscountPrice(Basket basket) {
                return discountPrice;
            }
        };
    }
}
