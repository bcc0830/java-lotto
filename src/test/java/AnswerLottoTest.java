import domain.AnswerLotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerLottoTest {
	static final List<Integer> sampleInput = new ArrayList<>();

	@BeforeAll
	static void setSampleInput() {
		sampleInput.add(1);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		sampleInput.add(6);
	}

	@Test
	void duplicateInBonusNumber() {
		LottoNumber sampleBonusNumber = new LottoNumber(1);
		assertThatThrownBy(() -> new AnswerLotto(sampleInput, sampleBonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void lottoNumberInAnswerNumbers() {
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(1);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isInAnswerNumbers(lottoNumber)).isEqualTo(1);
	}

	@Test
	void lottoNumberNotInAnswerNumbers() {
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(8);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isInAnswerNumbers(lottoNumber)).isEqualTo(0);
	}

	@Test
	void lottoNumberEqualsBonusNumber() {
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(7);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isSameWithBonusNumber(lottoNumber)).isTrue();
	}

	@Test
	void lottoNumberNotEqualsBonusNumber() {
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(6);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isSameWithBonusNumber(lottoNumber)).isFalse();
	}
}
