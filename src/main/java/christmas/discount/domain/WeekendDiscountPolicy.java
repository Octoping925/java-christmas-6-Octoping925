package christmas.discount.domain;

import christmas.basket.domain.Basket;
import christmas.menu.domain.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Supplier;

public class WeekendDiscountPolicy extends DiscountPolicy {
    private static final int MAIN_DISH_DISCOUNT_PRICE = 2023;
    private final Supplier<LocalDate> currentDateTimeSupplier;

    public WeekendDiscountPolicy(Supplier<LocalDate> currentDateTimeSupplier) {
        this.currentDateTimeSupplier = currentDateTimeSupplier;
    }

    @Override
    protected int calculateDiscountPrice(Basket basket) {
        LocalDate now = currentDateTimeSupplier.get();
        if (!isWeekend(now)) {
            return 0;
        }

        return getMainDishCount(basket) * MAIN_DISH_DISCOUNT_PRICE;
    }

    private int getMainDishCount(Basket basket) {
        return basket.searchMenu(Menu::isMainDish).size();
    }

    private boolean isWeekend(LocalDate now) {
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        return dayOfWeek == DayOfWeek.FRIDAY ||
                dayOfWeek == DayOfWeek.SATURDAY;
    }
}
