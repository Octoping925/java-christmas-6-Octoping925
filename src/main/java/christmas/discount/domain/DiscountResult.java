package christmas.discount.domain;

import java.util.List;

public record DiscountResult(
        int totalDiscountPrice,
        List<DiscountHistory> discountHistories
) {
    public record DiscountHistory(
            String discountName,
            int discountPrice
    ) {
    }
}
