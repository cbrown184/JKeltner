package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class JKeltnerCalculator {

    private final static int DEFAULT_PERIOD = 20;
    private final static int DEFAULT_SCALE = 6;
    private final static int DEFAULT_SMOOTING_FACTOR = 2;
    private final static int DEFAULT_ATR_MULTIPLIER = 2;

    private final ExponentialMovingAverage exponentialMovingAverage;
    private final AverageTrueRange averageTrueRange;
    private final BigDecimal atrMultiplier;

    public JKeltnerCalculator(ExponentialMovingAverage exponentialMovingAverage, AverageTrueRange averageTrueRange, BigDecimal atrMultiplier) {
        this.exponentialMovingAverage = exponentialMovingAverage;
        this.averageTrueRange = averageTrueRange;
        this.atrMultiplier = atrMultiplier;
    }

    public static class JKeltnerBuilder {

        private int period = DEFAULT_PERIOD;
        private int scale = DEFAULT_SCALE;
        private int smoothingFactor = DEFAULT_SMOOTING_FACTOR;
        private int atrMultiplier = DEFAULT_ATR_MULTIPLIER;

        public JKeltnerBuilder setPeriod(int period) {
            this.period = period;
            return this;
        }

        public JKeltnerBuilder setScale(int scale) {
            this.scale = scale;
            return this;
        }

        public JKeltnerBuilder setSmoothingFactor(int smoothingFactor) {
            this.smoothingFactor = smoothingFactor;
            return this;
        }

        public JKeltnerBuilder setAtrMultiplier(int atrMultiplier) {
            this.atrMultiplier = atrMultiplier;
            return this;
        }

        public JKeltnerCalculator build() {
            ExponentialMovingAverage exponentialMovingAverage = new ExponentialMovingAverage(period, scale, smoothingFactor);
            AverageTrueRange averageTrueRange = new AverageTrueRange(period, scale);
            return new JKeltnerCalculator(exponentialMovingAverage, averageTrueRange, new BigDecimal(atrMultiplier));
        }
    }

    public Optional<KeltnerBand> calculate(Candle candle) {
        Optional<BigDecimal> ema = exponentialMovingAverage.calculateEma(candle.close);
        Optional<BigDecimal> atr = averageTrueRange.calculateAverageTrueRange(candle);
        if(ema.isPresent() && atr.isPresent()) {
            BigDecimal emaBd = ema.get();
            BigDecimal atrBd = atr.get();
            BigDecimal upperBand = emaBd.add(atrMultiplier.multiply(atrBd));
            BigDecimal lowerBand = emaBd.subtract(atrMultiplier.multiply(atrBd));
            return Optional.of(new KeltnerBand(
                    upperBand,
                    emaBd,
                    lowerBand
            ));
        }
        return Optional.empty();
    }



    public Optional<KeltnerBand> calculate(Double high, Double low, Double close) {
        nullCheck(high, low, close);
        Candle candle = new Candle(
                BigDecimal.valueOf(high),
                BigDecimal.valueOf(low),
                BigDecimal.valueOf(close)
        );
        return calculate(candle);
    }

    Optional<KeltnerBand> calculate(BigDecimal high, BigDecimal low, BigDecimal close) {
        nullCheck(high, low, close);
        Candle candle = new Candle(
                high,
                low,
                close
        );
        return calculate(candle);
    }

    private void nullCheck(Object... object) {
        if(Arrays.stream(object).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }
    }
}
