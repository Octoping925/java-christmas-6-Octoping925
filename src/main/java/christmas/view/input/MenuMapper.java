package christmas.view.input;

import christmas.common.InvalidMenuNameException;
import christmas.domain.menu.*;

import java.util.Map;

public class MenuMapper {
    private static final Map<String, Menu> menuMap = Map.ofEntries(
            Map.entry("양송이수프", new MushroomSoup()),
            Map.entry("타파스", new Tapas()),
            Map.entry("시저샐러드", new CaesarSalad()),
            Map.entry("티본스테이크", new TBornSteak()),
            Map.entry("바비큐립", new BarbequeRib()),
            Map.entry("해산물파스타", new SeafoodPasta()),
            Map.entry("크리스마스파스타", new ChristmasPasta()),
            Map.entry("초코케이크", new ChocolateCake()),
            Map.entry("아이스크림", new Icecream()),
            Map.entry("제로콜라", new ZeroCoke()),
            Map.entry("레드와인", new RedWine()),
            Map.entry("샴페인", new Champagne())
    );

    public Menu mapMenuByMenuName(String menuName) {
        Menu menu = menuMap.get(menuName);

        if (menu == null) {
            throw new InvalidMenuNameException();
        }

        return menu;
    }
}
