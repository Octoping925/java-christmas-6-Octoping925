package christmas.domain.menu;

import christmas.common.InvalidMenuNameException;

import java.util.stream.Stream;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    TBORN_STEAK("티본스테이크", 55000, MenuType.MAIN_DISH),
    BARBEQUE_RIB("바비큐립", 54000, MenuType.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN_DISH),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICECREAM("아이스크림", 5000, MenuType.DESSERT),
    ZERO_COKE("제로콜라", 3000, MenuType.DRINK),
    RED_WINE("레드와인", 60000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25000, MenuType.DRINK),
    ;

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu of(String name) {
        return Stream.of(values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(InvalidMenuNameException::new);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isDessert() {
        return menuType == MenuType.DESSERT;
    }

    public boolean isDrink() {
        return menuType == MenuType.DRINK;
    }

    public boolean isMainDish() {
        return menuType == MenuType.MAIN_DISH;
    }
}
