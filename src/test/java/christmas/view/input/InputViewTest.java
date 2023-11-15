package christmas.view.input;

import christmas.domain.menu.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("InputView 클래스는")
class InputViewTest {
    private static final Consumer<String> FAKE_PRINT = msg -> {
    };

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

        @Nested
        @DisplayName("입력 방문 날짜가 유효하지 않으면 입력을 다시 받는다")
        class RetryInput {
            @DisplayName("입력 방문 날짜가 1보다 작으면 입력을 다시 받는다")
            @Test
            void getVisitDate() {
                // given
                Supplier<String> mockSupplier = createMockSupplier("-1", "15");
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                int result = view.getVisitDate();

                // then
                assertThat(result).isEqualTo(15);
            }

            @DisplayName("입력 방문 날짜가 31보다 크면 입력을 다시 받는다")
            @Test
            void getVisitDate2() {
                // given
                Supplier<String> mockSupplier = createMockSupplier("32", "15");

                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                int result = view.getVisitDate();

                // then
                assertThat(result).isEqualTo(15);
            }

            @DisplayName("입력 방문 날짜가 숫자가 아니면 입력을 다시 받는다")
            @Test
            void getVisitDate3() {
                // given
                Supplier<String> mockSupplier = createMockSupplier("abc", "15");
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                int result = view.getVisitDate();

                // then
                assertThat(result).isEqualTo(15);
            }
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


        @Nested
        @DisplayName("메뉴와 메뉴 개수가 유효하지 않으면 입력을 다시 받는다")
        class RetryInput {
            private static final String VALID_INPUT = "티본스테이크-1";

            @DisplayName("메뉴를 입력하지 않으면 입력을 다시 받는다")
            @Test
            void getVisitDate() {
                // given
                Supplier<String> mockSupplier = createMockSupplier("", VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

                // then
                assertThat(menuAndCount).containsEntry(Menu.TBORN_STEAK, 1);
            }

            @DisplayName("메뉴의 총합 개수가 20 초과라면 입력을 다시 받는다")
            @ParameterizedTest
            @ValueSource(strings = { "티본스테이크-21", "티본스테이크-5,바비큐립-10,초코케이크-7"})
            void getVisitDate2(String invalidInput) {
                // given
                Supplier<String> mockSupplier = createMockSupplier(invalidInput, VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

                // then
                assertThat(menuAndCount).containsEntry(Menu.TBORN_STEAK, 1);
            }

            @DisplayName("같은 메뉴를 중복으로 입력하면 입력을 다시 받는다")
            @Test
            void getVisitDate3() {
                // given
                String invalidInput = "티본스테이크-2,바비큐립-1,티본스테이크-1,제로콜라-1";
                Supplier<String> mockSupplier = createMockSupplier(invalidInput, VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

                // then
                assertThat(menuAndCount).containsEntry(Menu.TBORN_STEAK, 1);
            }

            @DisplayName("메뉴의 개수가 1보다 작으면 입력을 다시 받는다")
            @Test
            void getVisitDate4() {
                // given
                String invalidInput = "티본스테이크-0,바비큐립-1,초코케이크-2,제로콜라-1";
                Supplier<String> mockSupplier = createMockSupplier(invalidInput, VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

                // then
                assertThat(menuAndCount).containsEntry(Menu.TBORN_STEAK, 1);
            }

        }

    }

    private Supplier<String> createMockSupplier(String ...args) {
        Supplier<String> mockedSupplier = mock(Supplier.class);
        OngoingStubbing<String> when = when(mockedSupplier.get());
        for (String arg : args) {
            when = when.thenReturn(arg);
        }

        return mockedSupplier;
    }
}