package christmas.common;

public class InvalidMenuNameException extends ChristmasEventException {
    public InvalidMenuNameException() {
        super("해당하는 이름의 메뉴가 존재하지 않습니다");
    }
}
