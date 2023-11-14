package christmas.domain.menu;

import christmas.common.InvalidMenuNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("메뉴 이름을 받아 해당하는 메뉴를 반환한다")
    @Test
    void of() {
        // given
        String menuName = "양송이수프";

        // when
        Menu menu = Menu.of(menuName);

        // then
        assertThat(menu).isEqualTo(Menu.MUSHROOM_SOUP);
    }

    @DisplayName("메뉴가 없는 경우 예외를 던진다")
    @Test
    void ofNoName() {
        // given
        String notExistMenuName = "선풍기";

        // when & then
        assertThatThrownBy(() -> Menu.of(notExistMenuName))
                .isInstanceOf(InvalidMenuNameException.class);
    }
}