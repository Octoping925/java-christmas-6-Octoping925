package christmas.domain.discount;

import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Supplier;

public class WeekdayDiscountPolicy extends DiscountPolicy {
    private static final int DESSERT_DISCOUNT_PRICE = 2023;

    private final Supplier<LocalDate> currentDateTimeSupplier;

    public WeekdayDiscountPolicy(Supplier<LocalDate> currentDateTimeSupplier) {
        super("평일 할인");
        this.currentDateTimeSupplier = currentDateTimeSupplier;
    }

    @Override
    protected int calculateDiscountPrice(Basket basket) {
        LocalDate now = currentDateTimeSupplier.get();
        if (!isWeekday(now)) {
            return 0;
        }

        return getDessertCount(basket) * DESSERT_DISCOUNT_PRICE;
    }

    private int getDessertCount(Basket basket) {
        return basket.searchMenu(Menu::isDessert).size();
    }

    private boolean isWeekday(LocalDate now) {
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        return dayOfWeek == DayOfWeek.SUNDAY ||
                dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY;
    }
}
