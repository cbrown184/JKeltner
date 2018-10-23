package io.github.cbrown184.jkeltner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;

public class SimpleMovingAverageTest {

    @Test
    public void smaReturnsOptionalOfemptyIfNoElements() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(0, 0);
        Assertions.assertEquals(Optional.empty(), simpleMovingAverage.getAverage());
    }

    @Test
    public void smaReturnsOptionalOfemptyIfNotFull() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(2, 0);
        simpleMovingAverage.putPrice(BigDecimal.ONE);
        Assertions.assertEquals(Optional.empty(), simpleMovingAverage.getAverage());
    }

    @Test
    public void smaReturnsAverageTwoEqualElements() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(2, 0);
        simpleMovingAverage.putPrice(BigDecimal.ONE);
        simpleMovingAverage.putPrice(BigDecimal.ONE);
        Assertions.assertEquals(Optional.of(BigDecimal.ONE), simpleMovingAverage.getAverage());
    }

    @Test
    public void smaReturnsCorrectAverage() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(3, 4);
        simpleMovingAverage.putPrice(new BigDecimal("1.99999999"));
        simpleMovingAverage.putPrice(new BigDecimal("5.123910391039103913013"));
        simpleMovingAverage.putPrice(new BigDecimal("2.87608961896"));
        Assertions.assertEquals(Optional.of(new BigDecimal("3.3333")), simpleMovingAverage.getAverage());
    }

    @Test
    public void smaRunsOverWhenFull() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(3, 9);
        simpleMovingAverage.putPrice(new BigDecimal("1.99999999"));
        simpleMovingAverage.putPrice(new BigDecimal("5.123910391039103913013"));
        simpleMovingAverage.putPrice(new BigDecimal("2.87608961896"));
        simpleMovingAverage.putPrice(BigDecimal.TEN);
        Assertions.assertEquals(Optional.of(new BigDecimal("6.000000003")), simpleMovingAverage.getAverage());
    }
}
