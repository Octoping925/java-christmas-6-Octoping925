package christmas.view.input;

import christmas.domain.menu.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("InputView 클래스는")
class InputViewTest {
    private static final Consumer<String> FAKE_PRINT = msg -> {};

    @Nested
    @DisplayName("getVisitDate에서")
    class GetVisitDate {
        @Test
        @DisplayName("방문 날짜를 받는다")
        void getVisitDate() {
            // given
            String visitDate = "15";
            InputView view = new InputView(() -> visitDate, FAKE_PRINT);

            // when
            int result = view.getVisitDate();

            // then
            assertThat(result).isEqualTo(15);
        }
    }

    @Nested
    @DisplayName("getMenuAndCount에서")
    class GetMenuAndCount {
        @DisplayName("메뉴와 메뉴 개수를 받는다")
        @Test
        void getMenuAndCount() {
            // given
            String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
            InputView view = new InputView(() -> input, FAKE_PRINT);

            // when
            Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

            // then
            assertThat(menuAndCount)
                    .containsEntry(Menu.TBORN_STEAK, 1)
                    .containsEntry(Menu.BARBEQUE_RIB, 1)
                    .containsEntry(Menu.CHOCOLATE_CAKE, 2)
                    .containsEntry(Menu.ZERO_COKE, 1);
        }

    }
}