package org.jkeltner.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigDecimalUtilTest {

  BigDecimal bd = new BigDecimal(1);
  BigDecimal bd1 = new BigDecimal(123.4);
  BigDecimal bd2 = new BigDecimal(12123981233.4);
  BigDecimal bd3 = new BigDecimal(9149149);
  BigDecimal bd4 = new BigDecimal(0.000000000001);
  BigDecimal bd5 = new BigDecimal(0.00000000001);
  BigDecimal bd6 = new BigDecimal(0.1);
  BigDecimal bd7 = new BigDecimal(0.000000000011245123);
  BigDecimal bd8 = new BigDecimal(6500.123123);


  @Test
  public void maxTest(){
    Assertions.assertEquals(bd1, BigDecimalUtil.max(bd1));
    Assertions.assertEquals(bd1, BigDecimalUtil.max(bd1, bd5));
    Assertions.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2));
    Assertions.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2, bd3));
    Assertions.assertEquals(bd5, BigDecimalUtil.max(bd4, bd5));
    Assertions.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2, bd3, bd4, bd5));
  }

  @Test
  public void minTest(){
    Assertions.assertEquals(bd4, BigDecimalUtil.min(bd1, bd2, bd3, bd4, bd5));
  }

  @Test
  public void isSmallerThanTest(){
    Assertions.assertTrue(BigDecimalUtil.isSmallerThan(bd1, bd2));
    Assertions.assertFalse(BigDecimalUtil.isSmallerThan(bd2, bd1));
  }

  @Test
  public void isLargerThanTest(){
    Assertions.assertFalse(BigDecimalUtil.isLargerThan(bd1, bd2));
    Assertions.assertTrue(BigDecimalUtil.isLargerThan(bd2, bd1));
  }

  @Test
  public void isEqualToTest(){
    Assertions.assertFalse(BigDecimalUtil.isEqualTo(bd1, bd2));
    Assertions.assertTrue(BigDecimalUtil.isEqualTo(bd4, bd4));
  }

  @Test
  public void isGreaterThanOrEqualsTest(){
    Assertions.assertTrue(BigDecimalUtil.isGreaterThanOrEquals(bd5, bd4));
    Assertions.assertTrue(BigDecimalUtil.isGreaterThanOrEquals(bd4, bd4));
    Assertions.assertFalse(BigDecimalUtil.isGreaterThanOrEquals(bd4, bd5));
  }

  @Test
  public void getPositionOfMostSignificantDigitFromZero(){
    Assertions.assertEquals(  1, BigDecimalUtil.getMostSignificantDigitPosition(bd));
    Assertions.assertEquals(  3, BigDecimalUtil.getMostSignificantDigitPosition(bd1));
    Assertions.assertEquals(  11, BigDecimalUtil.getMostSignificantDigitPosition(bd2));
    Assertions.assertEquals(  7, BigDecimalUtil.getMostSignificantDigitPosition(bd3));
    Assertions.assertEquals(  -12, BigDecimalUtil.getMostSignificantDigitPosition(bd4));
    Assertions.assertEquals(  -11, BigDecimalUtil.getMostSignificantDigitPosition(bd5));
    Assertions.assertEquals(  0, BigDecimalUtil.getMostSignificantDigitPosition(bd6));
  }

  @Test
  public void setPrecisionTo5Digits(){
    Assertions.assertEquals(  1, BigDecimalUtil.roundToXDecimalPlaces(bd, 5).doubleValue(), 0);
    Assertions.assertEquals(  123.4, BigDecimalUtil.roundToXDecimalPlaces(bd1, 5).doubleValue(), 0);
    Assertions.assertEquals(  12124000000.0, BigDecimalUtil.roundToXDecimalPlaces(bd2, 5).doubleValue(), 0);
    Assertions.assertEquals(  9149100.0, BigDecimalUtil.roundToXDecimalPlaces(bd3, 5).doubleValue(), 0);
    Assertions.assertEquals(  0.0000000000010000, BigDecimalUtil.roundToXDecimalPlaces(bd4, 5).doubleValue(), 0);
    Assertions.assertEquals(  0.000000000011245, BigDecimalUtil.roundToXDecimalPlaces(bd7, 5).doubleValue(), 0);
  }

  @Test
  public void getMinTick(){
    Assertions.assertEquals(new BigDecimal(1000000).doubleValue(), BigDecimalUtil.getMinTick(bd2).doubleValue(), 0);
    Assertions.assertEquals(new BigDecimal(0.000000000000001).doubleValue(), BigDecimalUtil.getMinTick(bd7).doubleValue(), 0);
    Assertions.assertEquals(new BigDecimal(0.1).doubleValue(), BigDecimalUtil.getMinTick(bd8).doubleValue(), 0);
  }

  @Test
  public void getAverage(){
    List<BigDecimal> list = Arrays.asList(new BigDecimal(2), new BigDecimal(3), new BigDecimal(4));
    BigDecimal average = BigDecimalUtil.getAverage(list);
    Assertions.assertEquals(average, BigDecimal.valueOf(3));
  }

  @Test
  public void getAverageEmptyList() {
    List<BigDecimal> list = new ArrayList<>();
    BigDecimal average = BigDecimalUtil.getAverage(list);
    Assertions.assertEquals(average, BigDecimal.ZERO);
  }

  @Test
  public void getAverageZeroPriceList() {
    List<BigDecimal> list = Arrays.asList(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
    BigDecimal average = BigDecimalUtil.getAverage(list);
    Assertions.assertEquals(average, BigDecimal.ZERO);
  }

  @Test
  public void getAverageNegativePriceList() {
    List<BigDecimal> list = Arrays.asList(new BigDecimal(-2), new BigDecimal(-3), new BigDecimal(-4));
    BigDecimal average = BigDecimalUtil.getAverage(list);
    Assertions.assertEquals(average, BigDecimal.valueOf(-3));
  }
}
