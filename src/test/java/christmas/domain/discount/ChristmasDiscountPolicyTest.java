package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ChristmasDiscountPolicy 클래스는")
class ChristmasDiscountPolicyTest {
    @DisplayName("12월 25일이 지나지 않았다면 날짜에 따라 할인이 적용된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "12, 1, 1000",
            "12, 2, 1100",
            "12, 25, 3400"
    })
    void discount(int month, int day, int expectedDiscountPrice) {
        // given
        Supplier<LocalDate> beforeChristmas = () -> LocalDate.of(2023, month, day);
        ChristmasDiscountPolicy discountPolicy = new ChristmasDiscountPolicy(beforeChristmas);

        Basket basket = new Basket(List.of(
                Menu.SEAFOOD_PASTA,
                Menu.SEAFOOD_PASTA
        ));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }

    @DisplayName("12월 25일이 지나면 할인이 적용되지 않는다.")
    @Test
    void discount_after_christmas() {
        // given
        Supplier<LocalDate> afterChristmas = () -> LocalDate.of(2023, 12, 26);
        ChristmasDiscountPolicy discountPolicy = new ChristmasDiscountPolicy(afterChristmas);

        Basket basket = new Basket(List.of(
                Menu.SEAFOOD_PASTA,
                Menu.SEAFOOD_PASTA
        ));

        // when
        int discountPrice = discountPolicy.discount(basket);

        // then
        assertThat(discountPrice).isZero();
    }
}