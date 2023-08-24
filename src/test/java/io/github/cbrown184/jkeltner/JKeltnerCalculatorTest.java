package io.github.cbrown184.jkeltner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JKeltnerCalculatorTest {

    @Test
    public void JKeltnerCalculateDoubleHandlesNullArgs() {
        JKeltnerCalculateDoubleThrowsIllegalArgEx(null, 1d, 1d);
        JKeltnerCalculateDoubleThrowsIllegalArgEx(1d, null, 1d);
        JKeltnerCalculateDoubleThrowsIllegalArgEx(1d, 1d, null);
    }

    @Test
    public void JKeltnerCalculateBigDecimalHandlesNullArgs() {
        JKeltnerCalculateBigDecimalThrowsIllegalArgEx(null, BigDecimal.ONE, BigDecimal.ONE);
        JKeltnerCalculateBigDecimalThrowsIllegalArgEx(BigDecimal.ONE, null, BigDecimal.ONE);
        JKeltnerCalculateBigDecimalThrowsIllegalArgEx(BigDecimal.ONE, BigDecimal.ONE, null);
    }

    @Test
    public void JKeltnerCalculateReturnsOptionalEmptyWhenEmaReturnsOptionalEmpty() {
        ExponentialMovingAverage ema = Mockito.mock(ExponentialMovingAverage.class);
        AverageTrueRange atr = Mockito.mock(AverageTrueRange.class);
        when(ema.calculateEma(any())).thenReturn(Optional.empty());
        when(atr.calculateAverageTrueRange(any())).thenReturn(Optional.of(BigDecimal.ONE));
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator(ema, atr, BigDecimal.ONE);
        Assertions.assertEquals(Optional.empty(), jKeltnerCalculator.calculate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    public void JKeltnerCalculateReturnsOptionalEmptyWhenAtrReturnsOptionalEmpty() {
        ExponentialMovingAverage ema = Mockito.mock(ExponentialMovingAverage.class);
        AverageTrueRange atr = Mockito.mock(AverageTrueRange.class);
        when(ema.calculateEma(any())).thenReturn(Optional.of(BigDecimal.ONE));
        when(atr.calculateAverageTrueRange(any())).thenReturn(Optional.empty());
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator(ema, atr, BigDecimal.ONE);
        Assertions.assertEquals(Optional.empty(), jKeltnerCalculator.calculate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    public void JKeltnerCalculateReturnsOptionalEmptyWhenAtrAndEmaReturnsOptionalEmpty() {
        ExponentialMovingAverage ema = Mockito.mock(ExponentialMovingAverage.class);
        AverageTrueRange atr = Mockito.mock(AverageTrueRange.class);
        when(ema.calculateEma(any())).thenReturn(Optional.empty());
        when(atr.calculateAverageTrueRange(any())).thenReturn(Optional.empty());
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator(ema, atr, BigDecimal.ONE);
        Assertions.assertEquals(Optional.empty(), jKeltnerCalculator.calculate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    public void JKeltnerCalculateReturnsKeltnerBandWithAtrAndEma() {
        ExponentialMovingAverage ema = Mockito.mock(ExponentialMovingAverage.class);
        AverageTrueRange atr = Mockito.mock(AverageTrueRange.class);
        when(ema.calculateEma(any())).thenReturn(Optional.of(BigDecimal.TEN));
        when(atr.calculateAverageTrueRange(any())).thenReturn(Optional.of(BigDecimal.ONE));
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator(ema, atr, BigDecimal.ONE);
        Optional<KeltnerBand> band = jKeltnerCalculator.calculate(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
        Assertions.assertTrue(band.isPresent());
        KeltnerBand keltnerBand = band.get();
        Assertions.assertEquals(new BigDecimal("9"), keltnerBand.lowerBand);
        Assertions.assertEquals(new BigDecimal("10"), keltnerBand.midBand);
        Assertions.assertEquals(new BigDecimal("11"), keltnerBand.upperBand);
    }

    public static void JKeltnerCalculateDoubleThrowsIllegalArgEx(Double high, Double low, Double close) {
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator.JKeltnerBuilder().build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jKeltnerCalculator.calculate(high, low, close);
        });
    }

    public static void JKeltnerCalculateBigDecimalThrowsIllegalArgEx(BigDecimal high, BigDecimal low, BigDecimal close) {
        JKeltnerCalculator jKeltnerCalculator = new JKeltnerCalculator.JKeltnerBuilder().build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jKeltnerCalculator.calculate(high, low, close);
        });
    }
}
