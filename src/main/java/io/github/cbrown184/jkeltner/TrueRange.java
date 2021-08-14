package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

public class TrueRange {

    private Candle previousCandle;

    Optional<BigDecimal> calculate(Candle candle) {
        return Stream.of(newHighMinusNewLow(candle), newHighMinusPreviousClose(candle), newLowMinusPreviousClose(candle))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(BigDecimal::compareTo);
    }

    Optional<BigDecimal> calculate(Candle previousCandle, Candle newCandle) {
        this.previousCandle = previousCandle;
        return calculate(newCandle);
    }
    Optional<BigDecimal> newHighMinusNewLow(Candle newCandle) {
        return Optional.of(newCandle.high.subtract(newCandle.low));
    }

    Optional<BigDecimal> newHighMinusPreviousClose(Candle candle) {
        return previousCandle == null ? Optional.empty() : Optional.of(candle.high.subtract(previousCandle.close).abs());
    }

    Optional<BigDecimal> newLowMinusPreviousClose(Candle candle) {
        return previousCandle == null ? Optional.empty() : Optional.of(candle.low.subtract(previousCandle.close).abs());
    }
}
