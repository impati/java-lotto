package lotto.model;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Rank {
    DEFAULT(0, 0, false),
    THREE(3, 5000, false),
    FOUR(4, 50000, false),
    FIVE(5, 1500000, false),
    FIVE_WITH_BONUS(5, 30000000, true),
    SIX(6, 2000000000, false);

    private final int match;
    private final int reward;
    private final boolean needBonus;

    Rank(final int match, final int reward, final boolean needBonus) {
        this.match = match;
        this.reward = reward;
        this.needBonus = needBonus;
    }

    static Rank match(final int count, final boolean isBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == count)
                .filter(isBonusMatch(count, isBonus))
                .findAny()
                .orElse(DEFAULT);
    }

    private static Predicate<Rank> isBonusMatch(int count, boolean isBonus) {
        if (count == Rank.FIVE_WITH_BONUS.match) return rank -> rank.needBonus == isBonus;
        return rank -> true;
    }

    public int getMatch() {
        return match;
    }

    public int getReward() {
        return this.reward;
    }
}
