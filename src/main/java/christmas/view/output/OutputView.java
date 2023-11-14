package christmas.view.output;

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
        print("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printOrderMenuMessage() {
        print("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
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
        print(totalPrice + "원");
        print("");
    }

    public void printGiftMenu(Basket basket) {
        print("<증정 메뉴>");
        basket.getGiftMenus().forEach((key, value) -> print(key.getName() + " " + value + "개"));
    }

    public void printDiscountHistory(Basket basket, DiscountResult discountResult) {
        printBenefitHistory(basket, discountResult);
        printTotalBenefitPrice(discountResult, basket.getGiftMenus());
        print("");
    }

    private void printBenefitHistory(Basket basket, DiscountResult discountResult) {
        List<DiscountResult.DiscountHistory> discountHistories = discountResult.discountHistories();

        print("<혜택 내역>");
        discountHistories.forEach(discountHistory -> print(discountHistory.discountName() + ": -" + commarize(discountHistory.discountPrice()) + "원"));

        Map<Menu, Integer> giftMenus = basket.getGiftMenus();
        if(!giftMenus.isEmpty()) {
            int giftPrice = giftMenus.keySet().stream()
                    .mapToInt(Menu::getPrice)
                    .sum();

            print("증정 이벤트: -" + giftPrice + "원");
        }
    }

    private void printTotalBenefitPrice(DiscountResult discountResult, Map<Menu, Integer> giftMenus) {
        int giftTotalPrice = giftMenus.keySet().stream()
                .mapToInt(Menu::getPrice)
                .sum();
        
        int totalBenefitPrice = discountResult.totalDiscountPrice() + giftTotalPrice;
        
        print("<총혜택 금액>");
        print(totalBenefitPrice + "원");
    }

    private void print(String message) {
        outputResolver.accept(message);
    }

    private String commarize(int price) {
        return NumberFormat.getInstance().format(price);
    }
}
