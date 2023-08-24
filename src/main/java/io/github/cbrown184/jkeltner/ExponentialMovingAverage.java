package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

class ExponentialMovingAverage {

    private final BigDecimal smoothing;
    private final SimpleMovingAverage simpleMovingAverage;
    private Optional<BigDecimal> previousCandle = Optional.empty();
    private final int scale;

    /**
     * Creates an ExponentialMovingAverage calculator with the given parameters.
     *
     * @param period the number of periods to consider.
     * @param scale the scale for rounding.
     * @param smoothingFactor the smoothing factor.
     */
    ExponentialMovingAverage(int period, int scale, int smoothingFactor) {
        if (period <= 0 || scale <= 0 || smoothingFactor <= 0) {
            throw new IllegalArgumentException("Period, scale, and smoothingFactor must be positive.");
        }

        this.smoothing = BigDecimal.valueOf((double) smoothingFactor / (period + 1));
        this.simpleMovingAverage = new SimpleMovingAverage(period, scale);
        this.scale = scale;
    }

    /**
     * Calculates the EMA based on the given closing price.
     *
     * @param closingPrice the closing price to consider.
     * @return an Optional containing the EMA value, or empty if not calculable.
     */
    Optional<BigDecimal> calculateEma(BigDecimal closingPrice) {
        simpleMovingAverage.putPrice(closingPrice);
        Optional<BigDecimal> sma = simpleMovingAverage.getAverage();

        if (!sma.isPresent() || !previousCandle.isPresent()) {
            previousCandle = sma;
            return sma;
        }

        BigDecimal ema = computeEma(closingPrice, previousCandle.get());
        previousCandle = Optional.of(ema);

        return previousCandle;
    }

    /**
     * Computes the EMA value based on the given parameters.
     *
     * @param closingPrice the closing price.
     * @param previousEmaValue the previous EMA value.
     * @return the calculated EMA value.
     */
    private BigDecimal computeEma(BigDecimal closingPrice, BigDecimal previousEmaValue) {
        return closingPrice.subtract(previousEmaValue)
                .multiply(smoothing)
                .add(previousEmaValue)
                .setScale(scale, RoundingMode.HALF_UP);
    }
}
