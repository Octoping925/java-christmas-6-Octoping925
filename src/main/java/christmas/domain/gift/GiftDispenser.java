package christmas.domain.gift;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;

public class GiftDispenser {
    private static final int GIFT_TARGET_MINIMUM_PRICE = 120000;

    public void giveGift(Basket basket) {
        if(isGiftTarget(basket)) {
            basket.addGift(Menu.CHAMPAGNE);
        }
    }

    private boolean isGiftTarget(Basket basket) {
        return basket.getTotalPrice() >= GIFT_TARGET_MINIMUM_PRICE;
    }
}
