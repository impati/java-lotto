package lotto.model;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.service.LottoGenerator;

public class LottoPurchase {

	public static final int START_INCLUSIVE = 0;

	public static Lottos buyLottos(int lottoCount) {
		return new Lottos(IntStream.range(START_INCLUSIVE, lottoCount)
			.mapToObj(i -> new Lotto(LottoGenerator.generateLottoNumbers()))
			.collect(Collectors.toList()));
	}

}
