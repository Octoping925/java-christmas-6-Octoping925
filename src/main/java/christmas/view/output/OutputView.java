package christmas.view.output;

import christmas.domain.badge.Badge;
import christmas.domain.discount.DiscountResult;
import christmas.domain.menu.Basket;
import christmas.domain.menu.Menu;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OutputView {
    private final Consumer<String> outputResolver;

    public OutputView(Consumer<String> outputResolver) {
        this.outputResolver = outputResolver;
    }

    public void printRestaurantReservationDate() {
        print("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventBenefitMessage(int day) {
        print("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        print("");
    }

    public void printOrderedMenu(Map<Menu, Integer> menuAndCount) {
        print("<주문 메뉴>");
        menuAndCount.forEach((menu, count) -> print(menu.getName() + " " + count + "개"));
        print("");
    }

    public void printTotalPrice(int totalPrice) {
        print("<할인 전 총주문 금액>");
        print(commarize(totalPrice) + "원");
        print("");
    }

    public void printGiftMenu(Basket basket) {
        print("<증정 메뉴>");
        basket.getGiftMenus().forEach((key, value) -> print(key.getName() + " " + value + "개"));
    }

    public void printDiscountHistory(Basket basket, DiscountResult discountResult) {
        printBenefitHistory(basket, discountResult);
        printTotalBenefitPrice(discountResult, basket.getTotalGiftPrice());
        print("");
    }

    public void printExpectedPrice(Basket basket, DiscountResult discountResult) {
        int totalPrice = basket.getTotalPrice();
        int totalDiscountPrice = discountResult.totalDiscountPrice();

        int expectedPrice = totalPrice - totalDiscountPrice;

        print("<할인 후 예상 결제 금액>");
        print(commarize(expectedPrice) + "원");
    }

    public void printBadge(Badge badge) {
        print("<12월 이벤트 배지>");
        print(badge.getName());
    }

    private void printBenefitHistory(Basket basket, DiscountResult discountResult) {
        printDiscountHistory(discountResult.discountHistories());
        printGiftHistory(basket);
    }

    private void printDiscountHistory(List<DiscountResult.DiscountHistory> discountHistories) {
        print("<혜택 내역>");
        if (discountHistories.isEmpty()) {
            print("없음");
            return;
        }

        discountHistories.forEach(discountHistory -> print(discountHistory.discountName() + ": -" + commarize(discountHistory.discountPrice()) + "원"));
    }

    private void printGiftHistory(Basket basket) {
        int giftPrice = basket.getTotalGiftPrice();
        if (giftPrice > 0) {
            print("증정 이벤트: -" + commarize(giftPrice) + "원");
        }
    }

    private void printTotalBenefitPrice(DiscountResult discountResult, int giftTotalPrice) {
        int totalBenefitPrice = discountResult.totalDiscountPrice() + giftTotalPrice;

        print("<총혜택 금액>");
        print(commarize(totalBenefitPrice) + "원");
    }

    private void print(String message) {
        outputResolver.accept(message);
    }

    private String commarize(int price) {
        return NumberFormat.getInstance().format(price);
    }
}
