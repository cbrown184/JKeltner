package io.github.cbrown184.jkeltner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

public class AverageTrueRangeTest {

    @Test
    public void averageTrueRangeReturnsOptionalOfEmptyWhenNotEnoughData() {
        AverageTrueRange averageTrueRange = new AverageTrueRange(10, 10);
        Assertions.assertEquals(Optional.empty(), averageTrueRange.calculate(new Candle(10d, 10d, 10d)));
    }

    @Test
    public void averageTrueRangeIsCalculatedCorrectly() {
        AverageTrueRange averageTrueRange = new AverageTrueRange(10, 1);
        Optional<BigDecimal> atr = getAtr(averageTrueRange, "/atrTestData1.txt");
        Assertions.assertEquals(Optional.of(new BigDecimal("85.9")), atr);
    }

    @Test
    public void averageTrueRangeIsCalculatedCorrectlyScale6() {
        AverageTrueRange averageTrueRange = new AverageTrueRange(10, 6);
        Optional<BigDecimal> atr = getAtr(averageTrueRange, "/atrTestData2.txt");
        Assertions.assertEquals(Optional.of(new BigDecimal("0.000084")), atr);
    }

    @Test
    public void averageTrueRangeIsCalculatedCorrectlyScale10() {
        AverageTrueRange averageTrueRange = new AverageTrueRange(10, 10);
        Optional<BigDecimal> atr = getAtr(averageTrueRange, "/atrTestData3.txt");
        Assertions.assertEquals(Optional.of(new BigDecimal("0.0000000086")), atr);
    }

    @Test
    public void averageTrueRangeIsCalculatedCorrectlyScale2Period20() {
        AverageTrueRange averageTrueRange = new AverageTrueRange(20, 2);
        Optional<BigDecimal> atr = getAtr(averageTrueRange, "/atrTestData4.txt");
        Assertions.assertEquals(Optional.of(new BigDecimal("79.80")), atr);
    }

    public static Optional<BigDecimal> getAtr(AverageTrueRange averageTrueRange, String path) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(AverageTrueRangeTest.class.getResourceAsStream(path)));
        Optional<BigDecimal> atr = Optional.empty();
        for(String s: reader.lines().collect(Collectors.toList())) {
            String[] line = s.split("\t");
            atr = averageTrueRange.calculate( new Candle( new BigDecimal(line[0]), new BigDecimal(line[1]), new BigDecimal(line[2])));
        }
        return atr;
    }
}
