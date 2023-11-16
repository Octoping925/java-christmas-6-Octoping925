package christmas.common;

public class InvalidBasketSizeException extends ChristmasEventException {
    public InvalidBasketSizeException() {
        super("1개 이상, 20개 이하의 메뉴를 구매해야 합니다");
    }
}
