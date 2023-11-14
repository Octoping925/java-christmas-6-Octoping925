package christmas.domain.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuFixture {
    private MenuFixture() {
    }

    public static List<Menu> provideMenusByPrice(List<Integer> prices) {
        List<Menu> menus = new ArrayList<>();
        for (int price : prices) {
            menus.add(new TestMenu(price));
        }

        return menus;
    }

    public static List<Menu> provideMenuByType(List<MenuType> menuTypes) {
        List<Menu> menu = new ArrayList<>(menuTypes.size());
        for (MenuType menuType : menuTypes) {
            menu.add(new TestMenu(menuType));
        }
        return menu;
    }


}
