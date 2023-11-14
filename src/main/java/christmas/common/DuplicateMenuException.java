package christmas.common;

public class DuplicateMenuException extends ChristmasEventException {
    public DuplicateMenuException() {
        super("메뉴는 중복이 될 수 없습니다");
    }
}
