package christmas.discount.domain;

import christmas.basket.domain.Basket;

import java.time.LocalDate;
import java.util.function.Supplier;

public class ChristmasDiscountPolicy extends DiscountPolicy {
    private static final LocalDate christmas = LocalDate.of(2023, 12, 25);
    private static final int DISCOUNT_INITIAL_PRICE = 1000;
    private static final int DISCOUNT_INCREASE_PRICE = 100;
    private final Supplier<LocalDate> currentDateTimeSupplier;

    public ChristmasDiscountPolicy(Supplier<LocalDate> currentDateTimeSupplier) {
        super("크리스마스 디데이 할인");
        this.currentDateTimeSupplier = currentDateTimeSupplier;
    }

    @Override
    protected int calculateDiscountPrice(Basket basket) {
        LocalDate now = currentDateTimeSupplier.get();

        if (isAfterChristmas(now)) {
            return 0;
        }

        return getDiscountPrice(now);
    }

    private boolean isAfterChristmas(LocalDate now) {
        return now.isAfter(christmas);
    }

    private int getDiscountPrice(LocalDate now) {
        int dayOfMonth = now.getDayOfMonth();
        int increasedPrice = (dayOfMonth - 1) * DISCOUNT_INCREASE_PRICE;

        return DISCOUNT_INITIAL_PRICE + increasedPrice;
    }
}
