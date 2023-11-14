package christmas.domain.badge;

public class BadgeFactory {
    public Badge createBadge(int totalBenefitPrice) {
        if(totalBenefitPrice >= 20000) {
            return Badge.SANTA;
        }

        if(totalBenefitPrice >= 10000) {
            return Badge.TREE;
        }

        if(totalBenefitPrice >= 5000) {
            return Badge.STAR;
        }

        return Badge.NONE;
    }
}
