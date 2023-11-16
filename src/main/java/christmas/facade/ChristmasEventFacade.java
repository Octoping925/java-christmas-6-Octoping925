package christmas.facade;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeFactory;
import christmas.domain.discount.DiscountCalculator;
import christmas.domain.discount.DiscountResult;
import christmas.domain.gift.GiftDispenser;
import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

import java.util.Map;

public class ChristmasEventFacade {
    private final DiscountCalculator discountCalculator;
    private final GiftDispenser giftDispenser;
    private final BadgeFactory badgeFactory;
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventFacade(
            DiscountCalculator discountCalculator,
            GiftDispenser giftDispenser,
            BadgeFactory badgeFactory,
            InputView inputView,
            OutputView outputView
    ) {
        this.discountCalculator = discountCalculator;
        this.giftDispenser = giftDispenser;
        this.badgeFactory = badgeFactory;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        getReservationDay();
        Basket basket = getBasket();
        giveGift(basket);
        discount(basket);
        giveBadge(basket);
    }

    private void giveGift(Basket basket) {
        giftDispenser.giveGift(basket);
        outputView.printGiftMenu(basket);
    }


    private void getReservationDay() {
        outputView.printRestaurantReservationDate();
        int reservationDay = inputView.getVisitDate();
        outputView.printEventBenefitMessage(reservationDay);
    }

    private Basket getBasket() {
        Map<Menu, Integer> orderedMenu = inputView.getMenuAndCount();
        outputView.printOrderedMenu(orderedMenu);

        Basket basket = new Basket(orderedMenu);
        outputView.printTotalPrice(basket.getTotalPrice());

        return basket;
    }

    private void discount(Basket basket) {
        DiscountResult discountResult = discountCalculator.calculate(basket);
        outputView.printDiscountHistory(basket, discountResult);
        outputView.printExpectedPrice(basket, discountResult);
    }

    private void giveBadge(Basket basket) {
        Badge badge = badgeFactory.createBadge(basket.getTotalPrice() + basket.getTotalGiftPrice());
        outputView.printBadge(badge);
    }
}
