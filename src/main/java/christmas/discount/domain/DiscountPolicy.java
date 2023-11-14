package christmas.discount.domain;

import christmas.basket.domain.Basket;

public abstract class DiscountPolicy {
    private static final int MINIMUM_EVENT_PRICE = 10000;

    public final int discount(Basket basket) {
        if (!isDiscountTarget(basket)) {
            return 0;
        }

        return calculateDiscountPrice(basket);
    }

    protected abstract int calculateDiscountPrice(Basket basket);

    private boolean isDiscountTarget(Basket basket) {
        return basket.getTotalPrice() >= MINIMUM_EVENT_PRICE;
    }
}
