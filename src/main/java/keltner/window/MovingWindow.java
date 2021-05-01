package keltner.window;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class MovingWindow {

    private final int period;

    private final LinkedList<BigDecimal> window = new LinkedList<>();

    public List<BigDecimal> getValues() {
        return window;
    }

    public MovingWindow(int period) {
        this.period = period;
    }

    public void addValue(BigDecimal value) {
        window.addLast(value);
        if(window.size() > period) {
            window.pop();
        }
    }

    public void addValue(List<BigDecimal> valueList) {
        valueList.forEach(this::addValue);
    }

}
