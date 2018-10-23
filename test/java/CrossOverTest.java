import keltner.util.Crossover;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CrossOverTest {

  @Test
  public void didCrossOver() {
    BigDecimal previousClose = new BigDecimal(6700);
    BigDecimal currentClose = new BigDecimal(6700.02);
    BigDecimal high = new BigDecimal(6700.01);
    Assert.assertTrue(Crossover.crossOver(high, currentClose, previousClose));
  }

  @Test
  public void didnotCrossOver1() {
    BigDecimal previousClose = new BigDecimal(6700.02);
    BigDecimal currentClose = new BigDecimal(6700.02);
    BigDecimal high = new BigDecimal(6700.01);
    Assert.assertFalse(Crossover.crossOver(high, currentClose, previousClose));
  }

  @Test
  public void didnotCrossOver2() {
    BigDecimal previousClose = new BigDecimal(6700);
    BigDecimal currentClose = new BigDecimal(6700.02);
    BigDecimal high = new BigDecimal(6700.03);
    Assert.assertFalse(Crossover.crossOver(high, currentClose, previousClose));
  }

  @Test
  public void didnotCrossOver3() {
    BigDecimal previousClose = new BigDecimal(6700);
    BigDecimal currentClose = new BigDecimal(6700);
    BigDecimal high = new BigDecimal(6700.01);
    Assert.assertFalse(Crossover.crossOver(high, currentClose, previousClose));
  }
}
