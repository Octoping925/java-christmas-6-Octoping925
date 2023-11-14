package christmas.discount.domain;

import christmas.basket.domain.Basket;

import java.util.List;

public class DiscountCalculator {
    private final List<DiscountPolicy> discountPolicies;

    public DiscountCalculator(DiscountPolicy ...discountPolicies) {
        this.discountPolicies = List.of(discountPolicies);
    }

    public DiscountResult calculate(Basket basket) {
        List<DiscountResult.DiscountHistory> discountList = getDiscountList(basket);
        int totalDiscountPrice = getTotalDiscountPrice(discountList);

        return new DiscountResult(totalDiscountPrice, discountList);
    }

    private List<DiscountResult.DiscountHistory> getDiscountList(Basket basket) {
        return discountPolicies.stream()
                .map(discountPolicy -> discount(discountPolicy, basket))
                .filter(discountHistory -> discountHistory.discountPrice() > 0)
                .toList();
    }

    private DiscountResult.DiscountHistory discount(DiscountPolicy discountPolicy, Basket basket) {
        String policyName = discountPolicy.getPolicyName();
        int discountPrice = discountPolicy.discount(basket);

        return new DiscountResult.DiscountHistory(policyName, discountPrice);
    }

    private int getTotalDiscountPrice(List<DiscountResult.DiscountHistory> discountList) {
        return discountList.stream()
                .mapToInt(DiscountResult.DiscountHistory::discountPrice)
                .sum();
    }
}
