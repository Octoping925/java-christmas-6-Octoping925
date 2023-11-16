package christmas.domain.badge;

public enum Badge {
    TREE("트리"),
    STAR("산타"),
    NONE("없음"),
    SANTA("산타"),
    ;

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
