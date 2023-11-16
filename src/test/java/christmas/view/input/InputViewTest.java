package christmas.view.input;

import christmas.domain.menu.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
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
            @ParameterizedTest
            @ValueSource(strings = {"-1", "0"})
            void getVisitDate(String invalidInput) {
                checkInvalidInputLoop(invalidInput);
            }

            @DisplayName("입력 방문 날짜가 31보다 크면 입력을 다시 받는다")
            @Test
            void getVisitDate2() {
                checkInvalidInputLoop("32");
            }

            @DisplayName("입력 방문 날짜가 숫자가 아니면 입력을 다시 받는다")
            @Test
            void getVisitDate3() {
                checkInvalidInputLoop("abc");
            }

            private void checkInvalidInputLoop(String invalidInput) {
                final String VALID_INPUT = "15";
                final int VALID_RESULT = 15;

                // given
                Supplier<String> mockSupplier = createMockSupplier(invalidInput, VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                int result = view.getVisitDate();

                // then
                assertThat(result).isEqualTo(VALID_RESULT);
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
            @DisplayName("메뉴를 입력하지 않으면 입력을 다시 받는다")
            @Test
            void getMenuAndCount() {
                checkInvalidInputLoop("");
            }

            @DisplayName("메뉴의 총합 개수가 20 초과라면 입력을 다시 받는다")
            @ParameterizedTest
            @ValueSource(strings = {"티본스테이크-21", "티본스테이크-5,바비큐립-10,초코케이크-7"})
            void getMenuAndCount2(String invalidInput) {
                checkInvalidInputLoop(invalidInput);
            }

            @DisplayName("같은 메뉴를 중복으로 입력하면 입력을 다시 받는다")
            @Test
            void getMenuAndCount3() {
                checkInvalidInputLoop("티본스테이크-2,바비큐립-1,티본스테이크-1,제로콜라-1");
            }

            @DisplayName("메뉴의 개수가 1보다 작으면 입력을 다시 받는다")
            @ParameterizedTest
            @ValueSource(strings = {
                    "티본스테이크-0,바비큐립-1",
                    "티본스테이크--1,바비큐립-1",
            })
            void getMenuAndCount4(String invalidInput) {
                checkInvalidInputLoop(invalidInput);
            }

            @DisplayName("메뉴의 개수가 숫자가 아니면 입력을 다시 받는다")
            @Test
            void getMenuAndCount5() {
                checkInvalidInputLoop("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-abc");
            }

            @DisplayName("메뉴의 이름이 유효하지 않으면 입력을 다시 받는다")
            @ParameterizedTest
            @ValueSource(strings = {
                    "냉장고-1,바비큐립-1",
                    "-1,바비큐립-1",
            })
            void getMenuAndCount6(String invalidInput) {
                checkInvalidInputLoop(invalidInput);
            }

            @DisplayName("메뉴를 입력 형식에 맞지 않게 작성하면 입력을 다시 받는다")
            @ParameterizedTest
            @ValueSource(strings = {
                    "a-,bc-de",
                    "abc-de-fg,hj-1",
                    "abcdef",
                    "123456",
                    ",,,,,",
                    "------",
            })
            void getMenuAndCount7(String invalidInput) {
                checkInvalidInputLoop(invalidInput);
            }

            @DisplayName("메뉴가 음료 뿐이면 입력을 다시 받는다")
            @Test
            void getMenuAndCount8() {
                checkInvalidInputLoop("레드와인-5,제로콜라-1");
            }

            private void checkInvalidInputLoop(String invalidInput) {
                final String VALID_INPUT = "티본스테이크-1";
                final Map.Entry<Menu, Integer> VALID_ENTRY = Map.entry(Menu.TBORN_STEAK, 1);

                // given
                Supplier<String> mockSupplier = createMockSupplier(invalidInput, VALID_INPUT);
                InputView view = new InputView(mockSupplier, FAKE_PRINT);

                // when
                Map<Menu, Integer> menuAndCount = view.getMenuAndCount();

                // then
                assertThat(menuAndCount).contains(VALID_ENTRY);
            }
        }
    }

    private Supplier<String> createMockSupplier(String... args) {
        Supplier<String> mockedSupplier = mock(Supplier.class);
        OngoingStubbing<String> when = when(mockedSupplier.get());
        for (String arg : args) {
            when = when.thenReturn(arg);
        }

        return mockedSupplier;
    }
}