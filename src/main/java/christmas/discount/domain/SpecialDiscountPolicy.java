package christmas.discount.domain;

import christmas.basket.domain.Basket;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class SpecialDiscountPolicy extends DiscountPolicy {
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int DISCOUNT_PRICE = 1000;

    private final Supplier<LocalDate> currentDateSupplier;

    public SpecialDiscountPolicy(Supplier<LocalDate> currentDateSupplier) {
        this.currentDateSupplier = currentDateSupplier;
    }

    @Override
    protected int calculateDiscountPrice(Basket basket) {
        LocalDate now = currentDateSupplier.get();
        if(isSpecialDay(now)) {
            return DISCOUNT_PRICE;
        }

        return 0;
    }

    private boolean isSpecialDay(LocalDate now) {
        return SPECIAL_DAYS.contains(now.getDayOfMonth());
    }
}
