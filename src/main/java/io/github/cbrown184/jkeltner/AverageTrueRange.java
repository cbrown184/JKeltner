package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.util.Optional;

class AverageTrueRange {

    private final SimpleMovingAverage simpleMovingAverage;
    private final TrueRange trueRange = new TrueRange();

    public AverageTrueRange(int period, int scale) {
        this.simpleMovingAverage = new SimpleMovingAverage(period, scale);
    }

    Optional<BigDecimal> calculate(Candle candle) {
        Optional<BigDecimal> trueRange = this.trueRange.calculate(candle);
        if(trueRange.isPresent()) {
            simpleMovingAverage.putPrice(trueRange.get());
            return simpleMovingAverage.getAverage();
        }
        return Optional.empty();
    }
}
