package christmas.discount.domain;

import christmas.basket.domain.Basket;

import java.util.List;

public class DiscountCalculator {
    private final List<DiscountPolicy> discountPolicies;

    public DiscountCalculator(DiscountPolicy ...discountPolicies) {
        this.discountPolicies = List.of(discountPolicies);
    }

    public int calculate(Basket basket) {
        return discountPolicies.stream()
                .mapToInt(discountPolicy -> discountPolicy.discount(basket))
                .sum();
    }
}
