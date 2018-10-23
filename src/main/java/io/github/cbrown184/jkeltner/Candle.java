package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;

class Candle {

    public final BigDecimal high;
    public final BigDecimal low;
    public final BigDecimal close;

    Candle(BigDecimal high, BigDecimal low, BigDecimal close) {
        this.high = high;
        this.low = low;
        this.close = close;
    }

    Candle(double high, double low, double close) {
        this.high = BigDecimal.valueOf(high);
        this.low = BigDecimal.valueOf(low);
        this.close = BigDecimal.valueOf(close);
    }

}
