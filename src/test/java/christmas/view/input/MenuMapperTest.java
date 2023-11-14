package christmas.view.input;

import christmas.common.InvalidMenuNameException;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MushroomSoup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("MenuMapper 클래스는")
class MenuMapperTest {
    @DisplayName("메뉴 이름을 받아 해당하는 메뉴를 반환한다")
    @Test
    void mapMenuByMenuName() {
        // given
        MenuMapper menuMapper = new MenuMapper();
        String menuName = "양송이수프";

        // when
        Menu menu = menuMapper.mapMenuByMenuName(menuName);

        // then
        assertThat(menu).isInstanceOf(MushroomSoup.class);
    }

    @DisplayName("해당하는 메뉴가 없으면 예외를 던진다")
    @Test
    void mapMenuByMenuNameThrowException() {
        // given
        MenuMapper menuMapper = new MenuMapper();
        String notExistMenu = "선풍기";

        // when & then
        assertThatThrownBy(() -> menuMapper.mapMenuByMenuName(notExistMenu))
                .isInstanceOf(InvalidMenuNameException.class);
    }
}