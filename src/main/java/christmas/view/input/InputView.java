package christmas.view.input;

import christmas.common.*;
import christmas.domain.menu.Menu;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InputView {
    private static final int MENU_ORDER_LIMIT = 20;

    private final Supplier<String> inputResolver;
    private final Consumer<String> outputResolver;

    public InputView(Supplier<String> inputResolver, Consumer<String> outputResolver) {
        this.inputResolver = inputResolver;
        this.outputResolver = outputResolver;
    }

    public int getVisitDate() {
        return doLoop(() -> {
            String input = getInputWithMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            validateVisitDate(input);

            return Integer.parseInt(input);
        });
    }

    public Map<Menu, Integer> getMenuAndCount() {
        return doLoop(() -> {
            String input = getInputWithMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            validateInputEmpty(input);

            List<String> inputMenus = List.of(input.split(","));
            validateInputMenus(inputMenus);

            Map<Menu, Integer> menuAndCount = getMenuAndCount(inputMenus);
            validateMenuAndCount(menuAndCount);

            return menuAndCount;
        });
    }

    private Map<Menu, Integer> getMenuAndCount(List<String> inputMenus) {
        return inputMenus.stream()
                .map(inputMenu -> inputMenu.split("-"))
                .collect(Collectors.toMap(
                        nameAndCount -> Menu.of(nameAndCount[0]),
                        nameAndCount -> Integer.parseInt(nameAndCount[1])
                ));
    }

    private void validateMenuAndCount(Map<Menu, Integer> menuAndCount) {
        validateOnlyDrinkMenu(menuAndCount);
        validateMenuOverLimit(menuAndCount);
    }

    private static void validateMenuOverLimit(Map<Menu, Integer> menuAndCount) {
        int allMenuCount = menuAndCount.values().stream().mapToInt(Integer::valueOf).sum();
        if(allMenuCount > MENU_ORDER_LIMIT) {
            throw new InvalidBasketSizeException();
        }
    }

    private void validateOnlyDrinkMenu(Map<Menu, Integer> menuAndCount) {
        boolean isAllDrinkMenu = menuAndCount.keySet()
                .stream()
                .allMatch(Menu::isDrink);

        if(isAllDrinkMenu) {
            throw new OnlyDrinkMenuException();
        }
    }

    private void validateInputMenus(List<String> inputMenus) {
        validateInputMenuDuplicate(inputMenus);

        inputMenus.forEach(this::validateInputMenu);
    }

    private void validateInputEmpty(String input) {
        if(input.isBlank()) {
            throw new InvalidBasketSizeException();
        }
    }

    private void validateInputMenuDuplicate(List<String> inputMenus) {
        long distinctCount = inputMenus.stream()
                .map(inputMenu -> inputMenu.split("-")[0])
                .distinct()
                .count();

        if(distinctCount != inputMenus.size()) {
            throw new DuplicateMenuException();
        }
    }

    private void validateInputMenu(String inputMenu) {
        String[] menuNameAndCount = inputMenu.split("-");
        validateMenuCountIsNumber(menuNameAndCount[1]);

        int menuCount = Integer.parseInt(menuNameAndCount[1]);
        validateMenuCountIsPositive(menuCount);
    }

    private void validateMenuCountIsPositive(int menuCount) {
        if(menuCount <= 0) {
            throw new InvalidMenuCountException();
        }
    }

    private void validateMenuCountIsNumber(String menuCount) {
        try {
            Integer.parseInt(menuCount);
        } catch (NumberFormatException e) {
            throw new InvalidMenuCountException();
        }
    }

    private void validateVisitDate(String input) {
        validateVisitDateIsNumber(input);
        validateValidVisitDateRange(input);
    }

    private void validateVisitDateIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidVisitDateException();
        }
    }

    private void validateValidVisitDateRange(String input) {
        int visitDate = Integer.parseInt(input);

        if(visitDate < 1 || visitDate > 31) {
            throw new InvalidVisitDateException();
        }
    }

    private String getInputWithMessage(String message) {
        print(message);
        return inputResolver.get();
    }

    private void print(String message) {
        outputResolver.accept(message);
    }

    private <T> T doLoop(Supplier<T> supplier) {
        while(true) {
            try {
                return supplier.get();
            } catch (ChristmasEventException e) {
                print(e.getMessage());
            }
        }
    }
}
