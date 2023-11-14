package christmas.common;

public class OnlyDrinkMenuException extends ChristmasEventException {
    public OnlyDrinkMenuException() {
        super("음료 메뉴만 시킬 수는 없습니다");
    }
}
