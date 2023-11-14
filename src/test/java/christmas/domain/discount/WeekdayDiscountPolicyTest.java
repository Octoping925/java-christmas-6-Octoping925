package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.domain.menu.TestMenu;
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

@DisplayName("WeekdayDiscountPolicy 클래스는")
class WeekdayDiscountPolicyTest {
    @DisplayName("오늘이 평일(일~목)이면 디저트 메뉴 1개당 2023원을 할인한다")
    @ParameterizedTest
    @MethodSource("provideMenuTypes")
    void discount_with_weekday(List<MenuType> menuTypes, int expectedDiscountPrice) {
        // given
        Supplier<LocalDate> weekday = () -> LocalDate.of(2023, 12, 3);
        DiscountPolicy discountPolicy = new WeekdayDiscountPolicy(weekday);
        Basket basket = new Basket(provideMenuByType(menuTypes));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isEqualTo(expectedDiscountPrice);
    }

    @DisplayName("오늘이 평일(일~목)이 아니면 할인이 적용되지 않는다")
    @Test
    void discount_with_weekend() {
        // given
        Supplier<LocalDate> weekend = () -> LocalDate.of(2023, 12, 1);
        DiscountPolicy discountPolicy = new WeekdayDiscountPolicy(weekend);
        Basket basket = new Basket(provideMenuByType(List.of(
                MenuType.DESSERT,
                MenuType.DESSERT
        )));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isZero();
    }

    private static Stream<Arguments> provideMenuTypes() {
        return Stream.of(
                Arguments.of(List.of(MenuType.DESSERT, MenuType.DESSERT, MenuType.APPETIZER), 2023 * 2),
                Arguments.of(List.of(MenuType.DESSERT, MenuType.DESSERT, MenuType.DESSERT), 2023 * 3),
                Arguments.of(List.of(MenuType.MAIN_DISH), 0)
        );
    }

    private List<Menu> provideMenuByType(List<MenuType> menuTypes) {
        List<Menu> menu = new ArrayList<>(menuTypes.size());
        for (MenuType menuType : menuTypes) {
            menu.add(new TestMenu(menuType));
        }
        return menu;
    }
}
