package christmas.gift;

import christmas.basket.domain.Basket;
import christmas.menu.domain.Champagne;

public class GiftDispenser {
    private static final int GIFT_TARGET_MINIMUM_PRICE = 120000;

    public void giveGift(Basket basket) {
        if(isGiftTarget(basket)) {
            basket.addGift(new Champagne());
        }
    }

    private boolean isGiftTarget(Basket basket) {
        return basket.getTotalPrice() >= GIFT_TARGET_MINIMUM_PRICE;
    }
}
