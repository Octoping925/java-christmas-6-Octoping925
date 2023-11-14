package christmas.domain.menu;

import christmas.domain.menu.Menu;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Basket {
    private final Map<Menu, Integer> menus;
    private final Map<Menu, Integer> giftMenus;

    public Basket(Map<Menu, Integer> menus) {
        this.menus = menus;
        this.giftMenus = new EnumMap<>(Menu.class);
    }

    public Map<Menu, Integer> getGiftMenus() {
        return giftMenus;
    }

    public int getTotalPrice() {
        return menus.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Menu, Integer> searchMenu(Predicate<Menu> predicate) {
        return menus.entrySet()
                .stream()
                .filter(entry -> predicate.test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void addGift(Menu gift) {
        giftMenus.put(gift, giftMenus.getOrDefault(gift, 0) + 1);
    }
}
