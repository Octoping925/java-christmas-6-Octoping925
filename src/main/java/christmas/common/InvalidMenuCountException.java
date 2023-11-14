package christmas.common;

public class InvalidMenuCountException extends ChristmasEventException {
    public InvalidMenuCountException() {
        super("메뉴 개수는 1 이상의 숫자여야 합니다");
    }
}
