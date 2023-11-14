package christmas.basket.domain;

import christmas.menu.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Menu> menus;
    private final List<Menu> giftMenus;

    public Basket(List<Menu> menus) {
        this.menus = menus;
        this.giftMenus = new ArrayList<>();
    }

    public int getTotalPrice() {
        return menus.stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }
}
