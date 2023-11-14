package christmas.domain.discount;

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
