package lotto.domain;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(^[0-9]*$)");
    private static final String NUMBER_EXCEPTION_MESSAGE = "숫자가 아닌 값은 로또번호로 입력할 수 없습니다.";
    private static final String RANGE_EXCEPTION_MESSAGE = "로또 번호는 1과 45사이의 숫자여야 합니다.";

    private static final LottoNumber[] CACHE = IntStream.rangeClosed(Lotto.MININUM_NUMBER, Lotto.MAXIMUM_NUMBER)
            .mapToObj(LottoNumber::new)
            .toArray(LottoNumber[]::new);

    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int lottoNumber) {
        validateNumberRange(lottoNumber);
        return CACHE[--lottoNumber];
    }

    private static void validateNumberRange(int lottoNumber) {
        if (!isLottoNumber(lottoNumber)) {
            throw new IllegalArgumentException(RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static LottoNumber valueOf(String lottoNumber) {
        validateStringNumber(lottoNumber);
        return new LottoNumber(Integer.parseInt(lottoNumber));
    }

    private static void validateStringNumber(String lottoNumber) {
        if (!isNumber(lottoNumber)) {
            throw new IllegalArgumentException(NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isNumber(String lottoNumber) {
        return NUMBER_PATTERN.matcher(lottoNumber).find();
    }

    private static boolean isLottoNumber(int lottoNumber) {
        return lottoNumber >= Lotto.MININUM_NUMBER && lottoNumber <= Lotto.MAXIMUM_NUMBER;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.lottoNumber - other.lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }

    public int getValue() {
        return lottoNumber;
    }
}
