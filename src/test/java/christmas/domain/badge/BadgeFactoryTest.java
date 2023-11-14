package christmas.domain.badge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BadgeFactory는")
class BadgeFactoryTest {

    @DisplayName("총 혜택 금액이 2만원 이상이면 산타 배지를 생성한다")
    @Test
    void createBadge_santa() {
        // given
        BadgeFactory badgeFactory = new BadgeFactory();
        int totalBenefitPrice = 20000;

        // when
        Badge badge = badgeFactory.createBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총 혜택 금액이 1만원 이상이면 트리 배지를 생성한다")
    @Test
    void createBadge_tree() {
        // given
        BadgeFactory badgeFactory = new BadgeFactory();
        int totalBenefitPrice = 10000;

        // when
        Badge badge = badgeFactory.createBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @DisplayName("총 혜택 금액이 5천원 이상이면 스타 배지를 생성한다")
    @Test
    void createBadge_star() {
        // given
        BadgeFactory badgeFactory = new BadgeFactory();
        int totalBenefitPrice = 5000;

        // when
        Badge badge = badgeFactory.createBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @DisplayName("총 혜택 금액이 5천원 미만이면 배지를 생성하지 않는다")
    @Test
    void createBadge_none() {
        // given
        BadgeFactory badgeFactory = new BadgeFactory();
        int totalBenefitPrice = 4999;

        // when
        Badge badge = badgeFactory.createBadge(totalBenefitPrice);

        // then
        assertThat(badge).isEqualTo(Badge.NONE);
    }
}