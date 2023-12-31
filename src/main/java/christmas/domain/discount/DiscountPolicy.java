package christmas.domain.discount;

import christmas.domain.menu.Basket;

public abstract class DiscountPolicy {
    private static final int MINIMUM_EVENT_PRICE = 10000;
    private final String policyName;

    protected DiscountPolicy(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyName() {
        return policyName;
    }

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
