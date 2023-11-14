package christmas.discount.domain;

import christmas.basket.domain.Basket;
import christmas.menu.domain.Menu;
import christmas.menu.domain.MenuFixture;
import christmas.menu.domain.MenuType;
import christmas.menu.domain.TestMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WeekendDiscountPolicy 클래스는")
class WeekendDiscountPolicyTest {
    @DisplayName("오늘이 주말(금,토)이면 메인 메뉴 1개당 2023원을 할인한다")
    @ParameterizedTest
    @MethodSource("provideMenuTypes")
    void discount_with_weekday(List<MenuType> menuTypes, int expectedDiscountPrice) {
        // given
        Supplier<LocalDate> weekend = () -> LocalDate.of(2023, 12, 1);
        DiscountPolicy discountPolicy = new WeekendDiscountPolicy(weekend);
        Basket basket = new Basket(MenuFixture.provideMenuByType(menuTypes));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

    @DisplayName("오늘이 주말(금, 토)이 아니면 할인이 적용되지 않는다")
    @Test
    void discount_with_weekend() {
        // given
        Supplier<LocalDate> weekday = () -> LocalDate.of(2023, 12, 3);
        DiscountPolicy discountPolicy = new WeekendDiscountPolicy(weekday);
        Basket basket = new Basket(MenuFixture.provideMenuByType(List.of(
                MenuType.MAIN_DISH,
                MenuType.MAIN_DISH
        )));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isZero();
    }

    private static Stream<Arguments> provideMenuTypes() {
        return Stream.of(
                Arguments.of(List.of(MenuType.MAIN_DISH, MenuType.MAIN_DISH, MenuType.APPETIZER), 2023 * 2),
                Arguments.of(List.of(MenuType.MAIN_DISH, MenuType.MAIN_DISH, MenuType.MAIN_DISH), 2023 * 3),
                Arguments.of(List.of(MenuType.DESSERT), 0)
        );
    }


}
