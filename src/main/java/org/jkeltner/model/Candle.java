package org.jkeltner.model;

import java.math.BigDecimal;

public class Candle {
    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public long getTrades() {
        return trades;
    }

    public BigDecimal getHomeNotional() {
        return homeNotional;
    }

    public long getVolume() {
        return volume;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    String symbol;
    BigDecimal vwap;
    long trades;
    BigDecimal homeNotional;
    long volume;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;
    BigDecimal open;
    long timeStamp;

}
