package keltner.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

public class BigDecimalUtil {

  public static BigDecimal max(BigDecimal... bd) {
    return Arrays.stream(bd)
        .min(Comparator.reverseOrder())
        .orElse(BigDecimal.ZERO);
  }

  public static BigDecimal min(BigDecimal... bd) {
    return Arrays.stream(bd)
        .min(Comparator.naturalOrder())
        .orElse(BigDecimal.ZERO);
  }

  public static boolean isSmallerThan(BigDecimal a, BigDecimal b) {
    return a.compareTo(b) < 0;
  }

  public static boolean isLargerThan(BigDecimal a, BigDecimal b) {
    return a.compareTo(b) > 0;
  }

  public static boolean isEqualTo(BigDecimal a, BigDecimal b) {
    return a.compareTo(b) == 0;
  }

  public static boolean isLessThanOrEquals(BigDecimal a, BigDecimal b) {
    return isEqualTo(a, b) || isSmallerThan(a, b);
  }

  public static int getMostSignificantDigitPosition(BigDecimal a) {
    return a.precision() - a.scale();
  }

  public static BigDecimal getMinTick(BigDecimal a) {
    BigDecimal minTick = new BigDecimal(1);
    return minTick.movePointRight(getMostSignificantDigitPosition(a) - 5);
  }

  public static BigDecimal roundToXDecimalPlaces(BigDecimal a, int x) {
    int c = getMostSignificantDigitPosition(a);
    a = a.movePointLeft(c);
    a = a.setScale(x, RoundingMode.HALF_UP);
    a = a.movePointRight(c);
    return a;
  }

  public static boolean isGreaterThanOrEquals(BigDecimal a, BigDecimal b) {
    return a.compareTo(b) >= 0;
  }
}
