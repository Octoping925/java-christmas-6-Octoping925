package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WeekdayDiscountPolicy 클래스는")
class WeekdayDiscountPolicyTest {
    @DisplayName("오늘이 평일(일~목)이면 디저트 메뉴 1개당 2023원을 할인한다")
    @Test
    void discount_with_weekday() {
        // given
        Supplier<LocalDate> weekday = () -> LocalDate.of(2023, 12, 3);
        DiscountPolicy discountPolicy = new WeekdayDiscountPolicy(weekday);
        Basket basket = new Basket(Map.of(
                Menu.CHOCOLATE_CAKE, 1,
                Menu.TBORN_STEAK, 1,
                Menu.ICECREAM, 1
        ));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isEqualTo(2023 * 2);
    }

    @DisplayName("오늘이 평일(일~목)이 아니면 할인이 적용되지 않는다")
    @Test
    void discount_with_weekend() {
        // given
        Supplier<LocalDate> weekend = () -> LocalDate.of(2023, 12, 1);
        DiscountPolicy discountPolicy = new WeekdayDiscountPolicy(weekend);
        Basket basket = new Basket(Map.of(
                Menu.CHOCOLATE_CAKE, 1,
                Menu.TBORN_STEAK, 1,
                Menu.ICECREAM, 1
        ));

        // when
        int actualDiscountPrice = discountPolicy.discount(basket);

        // then
        assertThat(actualDiscountPrice).isZero();
    }
}
