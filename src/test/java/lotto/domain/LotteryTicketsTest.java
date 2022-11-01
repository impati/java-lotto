package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class LotteryTicketsTest {
    @Test
    void calculate_ranks() {
        LotteryTickets tickets = LotteryTickets.of(
                LotteryTicket.of(1, 2, 3, 4, 5, 6),
                LotteryTicket.of(1, 2, 3, 4, 5, 7),
                LotteryTicket.of(1, 2, 4, 5, 6, 8),
                LotteryTicket.of(1, 2, 4, 6, 7, 8),
                LotteryTicket.of(1, 2, 6, 7, 8, 9)
        );
        WinningTicket winningTicket = new WinningTicket("1, 2, 3, 4, 5, 6", 7);
        assertThat(tickets.calculateRanks(winningTicket).getRanks()).containsExactly(
                entry(Rank.FIRST, 1),
                entry(Rank.SECOND, 1),
                entry(Rank.THIRD, 1),
                entry(Rank.FORTH, 1),
                entry(Rank.NON_MATCH, 1)
        );
    }
}
