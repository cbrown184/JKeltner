package org.jkeltner;

import org.jkeltner.model.Candle;
import org.jkeltner.model.OrderFlags;
import org.jkeltner.util.BigDecimalUtil;
import org.jkeltner.util.Crossover;
import org.jkeltner.window.MovingAverage;

import java.math.BigDecimal;
import java.util.List;

public class JKeltner {
    private final MovingAverage movingAverageClose;
    private final MovingAverage movingAverageRange;
    private final int period;

    private boolean prevCrossLower = false;
    private boolean prevCrossUpper = false;
    private boolean prevCrossBcond = false;
    private boolean prevCrossScond = false;
    public boolean neverCrossedUpper = true;
    private boolean neverCrossedLower = true;

    private BigDecimal bPrice = null;
    private BigDecimal sPrice = null;

    private BigDecimal previousUpper;
    private BigDecimal previousLower;
    private BigDecimal prevbPrice;
    private BigDecimal prevsPrice;
    private BigDecimal upper;
    private BigDecimal lower;
    private BigDecimal ma;
    private boolean cancelBcond = false;
    public boolean cancelScond = false;
    BigDecimal rangema;

    public OrderFlags calculate(List<Candle> candleList) {
        if(candleList.isEmpty()) {
            return null;
        }
        Candle candle = candleList.get(candleList.size()-1);
        Candle prevCandle = candleList.get(candleList.size()-2);

        for(int i = 1; i < candleList.size(); i ++){
            movingAverageRange.addValue(
                    trueRange(candleList.get(i).getHigh(), candleList.get(i).getLow(), candleList.get(i-1).getClose())
            );
        }

        ma = BigDecimalUtil.getAverage(movingAverageClose.getValues());
        rangema = BigDecimalUtil.getAverage(movingAverageRange.getValues());

        upper = ma.add(rangema);
        lower = ma.subtract(rangema);
        boolean crossUpper = false;

        if (previousUpper != null) {
            crossUpper = Crossover.crossOver(upper, previousUpper, candle.getClose(), prevCandle.getClose());
        }

        if (crossUpper) {
            crossUpper(candle.getHigh());
        }

        boolean crossLower = false;
        if (previousLower != null) {
            crossLower = Crossover.crossUnder(lower, previousLower, candle.getClose(), prevCandle.getClose());
        }

        if (crossLower) {
            crossLower(candle.getLow());
        }


        boolean crossBcond = crossUpper ? true : prevCrossBcond;
        boolean crossScond = crossLower ? true : prevCrossScond;



        if(bPrice != null) {
            boolean b = BigDecimalUtil.isSmallerThan(candle.getClose(), ma);
            boolean c = BigDecimalUtil.isGreaterThanOrEquals(candle.getHigh(), bPrice);
            boolean d = b | c;
            cancelBcond = crossBcond && d;
        }


        if(sPrice != null) {
            boolean f = BigDecimalUtil.isLargerThan(candle.getClose(), ma);
            boolean g = BigDecimalUtil.isLessThanOrEquals(candle.getLow(), sPrice);
            boolean e = f | g;
            cancelScond = crossScond && e;
        }

        this.prevsPrice = sPrice;
        this.prevbPrice = bPrice;
        this.previousUpper = upper;
        this.previousLower = lower;
        this.prevCrossUpper = crossUpper;
        this.prevCrossLower = crossLower;
        this.prevCrossBcond = crossBcond;
        this.prevCrossScond = crossScond;

        OrderFlags orderFlags = new OrderFlags(cancelBcond, crossUpper, cancelScond, crossLower);
        return orderFlags;
    }

    public JKeltner(int period) {
        this.period = period;
        this.movingAverageClose = new MovingAverage(period);
        this.movingAverageRange = new MovingAverage(period);
    }


    public BigDecimal trueRange(BigDecimal high, BigDecimal low, BigDecimal prevClose) {
        final BigDecimal highMinusLow = high.subtract(low);
        final BigDecimal highMinusClose = high.subtract(prevClose).abs();
        final BigDecimal lowMinusClose = low.subtract(prevClose).abs();
        return BigDecimalUtil.max(highMinusLow, highMinusClose, lowMinusClose);
    }

    private BigDecimal getRange(BigDecimal high, BigDecimal low) {
        return high.subtract(low);
    }

    private void crossLower(BigDecimal low) {
        neverCrossedLower = false;
        sPrice = low.add(BigDecimalUtil.getMinTick(low.movePointRight(1)));
        //System.out.println(sPrice);
        sPrice = BigDecimalUtil.roundToXDecimalPlaces(sPrice, 12);
    }

    private void crossUpper(BigDecimal high) {
        neverCrossedUpper = false;
        bPrice = high.subtract(BigDecimalUtil.getMinTick(high.movePointLeft(1)));
        //System.out.println(bPrice);
        bPrice = BigDecimalUtil.roundToXDecimalPlaces(bPrice, 12);
    }

}
