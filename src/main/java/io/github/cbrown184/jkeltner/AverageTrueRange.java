package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.util.Optional;

class AverageTrueRange {

    private final SimpleMovingAverage simpleMovingAverage;
    private final TrueRange trueRange = new TrueRange();

    public AverageTrueRange(int period, int scale) {
        this.simpleMovingAverage = new SimpleMovingAverage(period, scale);
    }


    /**
     * Calculates the average true range for a given candle.
     *
     * @param candle the candle for which the average true range is to be calculated.
     * @return an Optional containing the average true range if calculable, or an empty Optional otherwise.
     */
    public Optional<BigDecimal> calculateAverageTrueRange(Candle candle) {
        return this.trueRange.calculate(candle)
                .flatMap(tr -> {
                    simpleMovingAverage.putPrice(tr);
                    return simpleMovingAverage.getAverage();
                });
    }

}
