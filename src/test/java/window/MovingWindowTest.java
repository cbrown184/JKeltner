package window;

import keltner.window.MovingWindow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class MovingWindowTest {

    @Test
    public void movingWindowSize1() {
        MovingWindow movingWindow = new MovingWindow(1);
        movingWindow.addValue(BigDecimal.ZERO);
        movingWindow.addValue(BigDecimal.ONE);
        movingWindow.addValue(BigDecimal.TEN);
        List<BigDecimal> values = movingWindow.getValues();
        Assertions.assertEquals(1, values.size());
        Assertions.assertEquals(BigDecimal.TEN, values.get(0));
    }

    @Test
    public void movingWindowSize20() {
        MovingWindow movingWindow = new MovingWindow(20);
        for(int i = 0; i < 50; i ++) {
            movingWindow.addValue(BigDecimal.valueOf(i));
        }
        List<BigDecimal> values = movingWindow.getValues();
        Assertions.assertEquals(20, values.size());
        for(int i = 0; i < values.size(); i ++) {
            Assertions.assertEquals(BigDecimal.valueOf(i + 30), values.get(i));
        }
    }

    @Test
    public void movingWindowSize0() {
        MovingWindow movingWindow = new MovingWindow(0);
        movingWindow.addValue(BigDecimal.ZERO);
        movingWindow.addValue(BigDecimal.ONE);
        movingWindow.addValue(BigDecimal.TEN);
        List<BigDecimal> values = movingWindow.getValues();
        Assertions.assertEquals(0, values.size());
    }
}
