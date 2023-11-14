package christmas.menu.domain;

public class TestMenu extends Menu {
    public TestMenu(String name, int price, MenuType menuType) {
        super(name, price, menuType);
    }

    public TestMenu(int price) {
        super("테스트메뉴", price, MenuType.MAIN_DISH);
    }

    public TestMenu(MenuType menuType) {
        super("테스트메뉴", 10000, menuType);
    }
}
