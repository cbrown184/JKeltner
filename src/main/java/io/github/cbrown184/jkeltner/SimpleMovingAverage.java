package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

class SimpleMovingAverage {

    private final int period;
    private final int scale;
    private final int ringBufferSize;
    private final BigDecimal[] data;
    private int head = 0;
    private boolean isFull = false;
    private BigDecimal runningTotal = BigDecimal.ZERO;

    public SimpleMovingAverage(int period, int scale) {
        this.period = period;
        this.scale = scale;
        this.ringBufferSize = period;
        this.data = new BigDecimal[period];
    }

    void putPrice(BigDecimal price) {
        if (data[head] != null) {
            runningTotal = runningTotal.subtract(data[head]);
        }
        data[head] = price;
        runningTotal = runningTotal.add(price);

        head++;
        if (head == ringBufferSize) {
            head = 0;
            isFull = true;
        }
    }

    Optional<BigDecimal> getAverage() {
        if (!isFull) {
            return Optional.empty();
        }
        return Optional.of(runningTotal.divide(BigDecimal.valueOf(period), scale, RoundingMode.HALF_UP));
    }

}
