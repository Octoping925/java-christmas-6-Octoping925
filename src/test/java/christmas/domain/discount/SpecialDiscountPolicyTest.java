package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.MenuFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SpecialDiscountPolicy 클래스는")
class SpecialDiscountPolicyTest {
    @DisplayName("오늘이 3, 10, 17, 24, 25, 31일이면 1000원을 할인한다")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void discount_with_special_day(int day) {
        // given
        Supplier<LocalDate> currentDateSupplier = () -> LocalDate.of(2023, 12, day);
        DiscountPolicy discountPolicy = new SpecialDiscountPolicy(currentDateSupplier);
        Basket basket = new Basket(MenuFixture.provideMenusByPrice(List.of(10000)));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("오늘이 3, 10, 17, 24, 25, 31일이 아니면 할인이 적용되지 않는다")
    @Test
    void discount_with_not_special_day() {
        // given
        Supplier<LocalDate> currentDateSupplier = () -> LocalDate.of(2023, 12, 1);
        DiscountPolicy discountPolicy = new SpecialDiscountPolicy(currentDateSupplier);
        Basket basket = new Basket(MenuFixture.provideMenusByPrice(List.of(10000)));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isZero();
    }
}
