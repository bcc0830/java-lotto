package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    private final LottoNumberGenerator lottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
    private final WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

    @DisplayName("수동 없이 자동 로또 티켓만을 구매한다.")
    @Test
    void getLottoResult() {
        ManualTickets manualTickets = ManualTickets.from(Collections.emptyList());
        LottoTickets lottoTickets = LottoTickets.generate(manualTickets, 3, lottoNumberGenerator);

        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        long secondPrizeTicketCounts = lottoResult.getStatistics().get(LottoRank.SECOND_PRIZE);

        assertThat(secondPrizeTicketCounts).isEqualTo(3);
    }

    @DisplayName("자동 없이 수동 로또 티켓만을 구매한다")
    @Test
    void makeManualLottoTickets() {
        String[] split = "1, 2, 3, 4, 5, 6".split(", ");
        ManualTickets manualTickets = ManualTickets.from(Arrays.asList(split, split));
        LottoTickets lottoTickets = LottoTickets.generate(manualTickets, 0, lottoNumberGenerator);

        long secondPrizeTicketCounts = lottoTickets.checkResult(winningLottoTicket)
                .getStatistics()
                .get(LottoRank.SECOND_PRIZE);

        assertThat(secondPrizeTicketCounts).isEqualTo(2);
    }

    @DisplayName("자동과 수동을 혼합해 구매한 로또 티켓의 개수를 반환한다")
    @Test
    void getTicketCounts() {
        String[] split = "1, 2, 3, 4, 5, 6".split(", ");
        ManualTickets manualTickets = ManualTickets.from(Arrays.asList(split, split));
        LottoTickets lottoTickets = LottoTickets.generate(manualTickets, 3, lottoNumberGenerator);

        int ticketCounts = lottoTickets.getTicketCounts();

        assertThat(ticketCounts).isEqualTo(5);
    }
}
