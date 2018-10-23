package io.github.cbrown184.jkeltner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;

public class ExponentialMovingAverageTest {

    @Test
    public void emaReturnsOptionalOfEmptyIfSmaIsNotFull() {
        ExponentialMovingAverage exponentialMovingAverage = new ExponentialMovingAverage(3, 3, 2);
        Assertions.assertEquals(Optional.empty(), exponentialMovingAverage.calculateEma(new BigDecimal("123.12")));
        Assertions.assertEquals(Optional.empty(), exponentialMovingAverage.calculateEma(new BigDecimal("123.12")));
        Assertions.assertNotEquals(Optional.empty(), exponentialMovingAverage.calculateEma(new BigDecimal("123.12")));
    }

    @Test
    public void emaIsCalculatedCorrectly() {
        ExponentialMovingAverage exponentialMovingAverage = new ExponentialMovingAverage(10, 2, 2);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ExponentialMovingAverageTest.class.getResourceAsStream("/emaTestInput.txt")));
        reader.lines().forEach(s -> exponentialMovingAverage.calculateEma(new BigDecimal(s)));
        Assertions.assertEquals(Optional.of(new BigDecimal("22.92")), exponentialMovingAverage.calculateEma(new BigDecimal("22.17")));
    }

}
