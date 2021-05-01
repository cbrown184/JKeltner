package keltner.util;

import java.math.BigDecimal;

public class Crossover {

  public static boolean crossOver(BigDecimal bar, BigDecimal currentClose, BigDecimal previousClose) {
    return BigDecimalUtil.isLargerThan(currentClose, bar) && BigDecimalUtil.isSmallerThan(previousClose, bar);
  }

  public static boolean crossOver(BigDecimal bar, BigDecimal prevBar, BigDecimal currentClose, BigDecimal previousClose) {
    return BigDecimalUtil.isLargerThan(currentClose, bar) && BigDecimalUtil.isSmallerThan(previousClose, prevBar);
  }

  public static boolean crossUnder(BigDecimal bar, BigDecimal prevBar, BigDecimal currentClose, BigDecimal previousClose) {
    return BigDecimalUtil.isSmallerThan(currentClose, bar) && BigDecimalUtil.isLargerThan(previousClose, prevBar);
  }

}
