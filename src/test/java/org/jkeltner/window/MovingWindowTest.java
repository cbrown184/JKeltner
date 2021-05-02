package org.jkeltner.window;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class MovingWindowTest {

    @Test
    public void movingWindowSize1() {
        MovingAverage movingAverage = new MovingAverage(1);
        Assertions.assertFalse(movingAverage.isFull());
        movingAverage.addValue(BigDecimal.ZERO);
        Assertions.assertTrue(movingAverage.isFull());
        movingAverage.addValue(BigDecimal.ONE);
        movingAverage.addValue(BigDecimal.TEN);
        List<BigDecimal> values = movingAverage.getValues();
        Assertions.assertEquals(1, values.size());
        Assertions.assertEquals(BigDecimal.TEN, values.get(0));
    }

    @Test
    public void movingWindowSize20() {
        MovingAverage movingAverage = new MovingAverage(20);
        for(int i = 0; i < 50; i ++) {
            movingAverage.addValue(BigDecimal.valueOf(i));
        }
        Assertions.assertTrue(movingAverage.isFull());
        List<BigDecimal> values = movingAverage.getValues();
        Assertions.assertEquals(20, values.size());
        for(int i = 0; i < values.size(); i ++) {
            Assertions.assertEquals(BigDecimal.valueOf(i + 30), values.get(i));
        }
    }

    @Test
    public void movingWindowSize0() {
        MovingAverage movingAverage = new MovingAverage(0);
        Assertions.assertTrue(movingAverage.isFull());
        movingAverage.addValue(BigDecimal.ZERO);
        movingAverage.addValue(BigDecimal.ONE);
        movingAverage.addValue(BigDecimal.TEN);
        List<BigDecimal> values = movingAverage.getValues();
        Assertions.assertEquals(0, values.size());
    }
}
