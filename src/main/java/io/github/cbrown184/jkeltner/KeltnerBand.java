package io.github.cbrown184.jkeltner;

import java.math.BigDecimal;

public class KeltnerBand {

    public final BigDecimal upperBand;
    public final BigDecimal midBand;
    public final BigDecimal lowerBand;

    public KeltnerBand(BigDecimal upperBand, BigDecimal midBand, BigDecimal lowerBand) {
        this.upperBand = upperBand;
        this.midBand = midBand;
        this.lowerBand = lowerBand;
    }


    @Override
    public String toString() {
        return "KeltnerBand{" +
                "upperBand=" + upperBand +
                ", midBand=" + midBand +
                ", lowerBand=" + lowerBand +
                '}';
    }
}
