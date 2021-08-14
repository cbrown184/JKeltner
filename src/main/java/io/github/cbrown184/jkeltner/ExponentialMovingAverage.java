package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

class ExponentialMovingAverage {

    private final BigDecimal smoothing;
    private final SimpleMovingAverage simpleMovingAverage;
    private BigDecimal previousCandle;
    private final int scale;

    ExponentialMovingAverage(int period, int scale, int smoothingFactor) {
        this.smoothing = BigDecimal.valueOf((double) smoothingFactor / (period + 1));
        this.simpleMovingAverage = new SimpleMovingAverage(period, scale);
        this.scale = scale;
    }

    Optional<BigDecimal> calculateEma(BigDecimal closingPrice) {
        simpleMovingAverage.putPrice(closingPrice);
        Optional<BigDecimal> sma = simpleMovingAverage.getAverage();

        if(!sma.isPresent()) {
            return sma;
        }

        if(previousCandle == null) {
            previousCandle = sma.get();
            return Optional.of(previousCandle);
        }

        BigDecimal ema = closingPrice.subtract(previousCandle)
                .multiply(smoothing)
                .add(previousCandle)
                .setScale(scale, RoundingMode.HALF_UP);
        previousCandle = ema;
        return Optional.of(ema);
    }
}
