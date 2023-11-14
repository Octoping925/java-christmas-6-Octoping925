package christmas.menu.domain;

public abstract class Menu {
    private final String name;
    private final int price;
    private final MenuType menuType;

    protected Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
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
