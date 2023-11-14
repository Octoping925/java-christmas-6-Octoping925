package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Basket은")
class BasketTest {
    @DisplayName("장바구니에 있는 메뉴의 가격 총합을 반환한다")
    @Test
    void calculateTotalPrice() {
        // given
        Map<Menu, Integer> menus = Map.of(
                Menu.CHOCOLATE_CAKE, 1,
                Menu.BARBEQUE_RIB, 1,
                Menu.ICECREAM, 1
        );

        Basket basket = new Basket(menus);

        // when
        int totalPrice = basket.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(74000);
    }

    @DisplayName("장바구니 안의 메뉴를 필터링해서 검색할 수 있다")
    @Test
    void searchMenu() {
        // given
        Basket basket = new Basket(Map.of(
                Menu.CHOCOLATE_CAKE, 1,
                Menu.BARBEQUE_RIB, 2,
                Menu.RED_WINE, 1
        ));

        // when
        Map<Menu, Integer> searchResult = basket.searchMenu(Menu::isDrink);

        // then
        assertThat(searchResult).containsEntry(Menu.RED_WINE, 1);
    }
}
