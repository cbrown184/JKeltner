import keltner.util.BigDecimalUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

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
    Assert.assertEquals(bd1, BigDecimalUtil.max(bd1));
    Assert.assertEquals(bd1, BigDecimalUtil.max(bd1, bd5));
    Assert.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2));
    Assert.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2, bd3));
    Assert.assertEquals(bd5, BigDecimalUtil.max(bd4, bd5));
    Assert.assertEquals(bd2, BigDecimalUtil.max(bd1, bd2, bd3, bd4, bd5));
  }

  @Test
  public void minTest(){
    Assert.assertEquals(bd4, BigDecimalUtil.min(bd1, bd2, bd3, bd4, bd5));
  }

  @Test
  public void isSmallerThanTest(){
    Assert.assertTrue(BigDecimalUtil.isSmallerThan(bd1, bd2));
    Assert.assertFalse(BigDecimalUtil.isSmallerThan(bd2, bd1));
  }

  @Test
  public void isLargerThanTest(){
    Assert.assertFalse(BigDecimalUtil.isLargerThan(bd1, bd2));
    Assert.assertTrue(BigDecimalUtil.isLargerThan(bd2, bd1));
  }

  @Test
  public void isEqualToTest(){
    Assert.assertFalse(BigDecimalUtil.isEqualTo(bd1, bd2));
    Assert.assertTrue(BigDecimalUtil.isEqualTo(bd4, bd4));
  }

  @Test
  public void isGreaterThanOrEqualsTest(){
    Assert.assertTrue(BigDecimalUtil.isGreaterThanOrEquals(bd5, bd4));
    Assert.assertTrue(BigDecimalUtil.isGreaterThanOrEquals(bd4, bd4));
    Assert.assertFalse(BigDecimalUtil.isGreaterThanOrEquals(bd4, bd5));
  }

  @Test
  public void getPositionOfMostSignificantDigitFromZero(){
    Assert.assertEquals(  1, BigDecimalUtil.getMostSignificantDigitPosition(bd));
    Assert.assertEquals(  3, BigDecimalUtil.getMostSignificantDigitPosition(bd1));
    Assert.assertEquals(  11, BigDecimalUtil.getMostSignificantDigitPosition(bd2));
    Assert.assertEquals(  7, BigDecimalUtil.getMostSignificantDigitPosition(bd3));
    Assert.assertEquals(  -12, BigDecimalUtil.getMostSignificantDigitPosition(bd4));
    Assert.assertEquals(  -11, BigDecimalUtil.getMostSignificantDigitPosition(bd5));
    Assert.assertEquals(  0, BigDecimalUtil.getMostSignificantDigitPosition(bd6));
  }

  @Test
  public void setPrecisionTo5Digits(){
    Assert.assertEquals(  1, BigDecimalUtil.roundToXDecimalPlaces(bd, 5).doubleValue(), 0);
    Assert.assertEquals(  123.4, BigDecimalUtil.roundToXDecimalPlaces(bd1, 5).doubleValue(), 0);
    Assert.assertEquals(  12124000000.0, BigDecimalUtil.roundToXDecimalPlaces(bd2, 5).doubleValue(), 0);
    Assert.assertEquals(  9149100.0, BigDecimalUtil.roundToXDecimalPlaces(bd3, 5).doubleValue(), 0);
    Assert.assertEquals(  0.0000000000010000, BigDecimalUtil.roundToXDecimalPlaces(bd4, 5).doubleValue(), 0);
    Assert.assertEquals(  0.000000000011245, BigDecimalUtil.roundToXDecimalPlaces(bd7, 5).doubleValue(), 0);
  }

  @Test
  public void getMinTick(){
    Assert.assertEquals(new BigDecimal(1000000).doubleValue(), BigDecimalUtil.getMinTick(bd2).doubleValue(), 0);
    Assert.assertEquals(new BigDecimal(0.000000000000001).doubleValue(), BigDecimalUtil.getMinTick(bd7).doubleValue(), 0);
    Assert.assertEquals(new BigDecimal(0.1).doubleValue(), BigDecimalUtil.getMinTick(bd8).doubleValue(), 0);
  }
}
