package io.github.cbrown184.jkeltner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class TrueRangeTest {

    @Test
    public void todaysHighMinusTodaysLow() {
        TrueRange trueRange = new TrueRange();
        Candle today = new Candle(100, 1, 2);
        Candle yesterday = new Candle(50, 50, 50);
        Assertions.assertEquals(Optional.of(new BigDecimal("99.0")), trueRange.calculate(yesterday, today));
    }

    @Test
    public void todaysHighMinusTodaysLowWhenYesterdayIsNull() {
        TrueRange trueRange = new TrueRange();
        Candle today = new Candle(100, 1, 2);
        Assertions.assertEquals(Optional.of(new BigDecimal("99.0")), trueRange.calculate(null, today));
    }

    @Test
    public void todaysLowMinusYesterdaysClose() {
        TrueRange trueRange = new TrueRange();
        Candle today = new Candle(100, 1, 2);
        Candle yesterday = new Candle(50, 50, 1000);
        Assertions.assertEquals(Optional.of(new BigDecimal("999.0")), trueRange.calculate(yesterday, today));
    }

    @Test
    public void todaysHighMinusYesterdaysClose() {
        TrueRange trueRange = new TrueRange();
        Candle today = new Candle(0, 1, 2);
        Candle yesterday = new Candle(50, 50, 1000);
        Assertions.assertEquals(Optional.of(new BigDecimal("1000.0")), trueRange.calculate(yesterday, today));
    }

}
