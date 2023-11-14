package christmas.gift;

import christmas.basket.domain.Basket;
import christmas.menu.domain.TestMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GiftDispenser 클래스는")
class GiftDispenserTest {
    @DisplayName("장바구니를 받아, 할인 전 총 주문 금액이 12만원 이상이라면 증정품을 지급한다")
    @Test
    void dispense_gift() {
        // given
        GiftDispenser dispenser = new GiftDispenser();
        Basket basket = new Basket(List.of(
                new TestMenu(150000)
        ));

        // when
        dispenser.giveGift(basket);

        // then
        assertThat(basket.getGiftMenus()).hasSize(1);
    }

    @DisplayName("장바구니를 받아, 할인 전 총 주문 금액이 12만원 이상이 아니라면 증정품을 지급하지 않는다")
    @Test
    void not_dispense_gift() {
        // given
        GiftDispenser dispenser = new GiftDispenser();
        Basket basket = new Basket(List.of(
                new TestMenu(10000)
        ));

        // when
        dispenser.giveGift(basket);

        // then
        assertThat(basket.getGiftMenus()).isEmpty();
    }
}