package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Optional;

class SimpleMovingAverage {

    private final BigDecimal period;
    private final int ringBufferSize;
    private int head = 0;
    private final BigDecimal[] data;
    private final int scale;
    private boolean isFull;

    public SimpleMovingAverage(int period, int scale) {
        ringBufferSize = period;
        this.period = BigDecimal.valueOf(period);
        data = new BigDecimal[period];
        this.scale = scale;
    }

    void putPrice(BigDecimal price) {
        data[head] = price;
        head ++;
        if(head == ringBufferSize) {
            head = 0;
            isFull = true;
        }
    }

    Optional<BigDecimal> getAverage() {
        if(!isFull) {
            return Optional.empty();
        }
        return Arrays.stream(data)
                .reduce(BigDecimal::add)
                .map(bd -> bd.divide(period, scale, RoundingMode.HALF_UP));
    }

}
