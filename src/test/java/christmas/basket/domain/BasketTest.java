package christmas.basket.domain;

import christmas.menu.domain.Menu;
import christmas.menu.domain.TestMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Basket은")
class BasketTest {
    @DisplayName("장바구니에 있는 메뉴의 가격 총합을 반환한다")
    @ParameterizedTest
    @MethodSource("provideMenusAndTotalPrice")
    void calculateTotalPrice(List<Integer> prices, int expectedTotalPrice) {
        // given
        List<Menu> menus = provideMenusByPrice(prices);
        Basket basket = new Basket(menus);

        // when
        int totalPrice = basket.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    private static Stream<Arguments> provideMenusAndTotalPrice() {
        return Stream.of(
                Arguments.of(List.of(1000, 2000, 3000), 6000),
                Arguments.of(List.of(1000, 2000, 3000, 4000), 10000),
                Arguments.of(List.of(), 0)
        );
    }

    private List<Menu> provideMenusByPrice(List<Integer> prices) {
        List<Menu> menus = new ArrayList<>();
        for (int price : prices) {
            menus.add(new TestMenu(price));
        }

        return menus;
    }
}
