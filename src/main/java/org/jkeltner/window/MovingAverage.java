package org.jkeltner.window;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class MovingAverage {

    private final int period;
    private final LinkedList<BigDecimal> window = new LinkedList<>();

    public List<BigDecimal> getValues() {
        return window;
    }

    public MovingAverage(int period) {
        this.period = period;
    }

    public void addValue(BigDecimal value) {
        window.addLast(value);
        if(window.size() > period) {
            window.pop();
        }
    }

    public boolean isFull() {
        return this.period == window.size();
    }
}
