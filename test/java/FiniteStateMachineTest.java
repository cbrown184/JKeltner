import fsm.FSMFactory;
import fsm.events.Always;
import fsm.events.CancelSCond;
import fsm.events.CrossLowerKeltnerChannel;
import fsm.events.CrossUpperKeltnerChannel;
import fsm.events.LongFilled;
import fsm.events.ShortFilled;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.FiniteStateMachineException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FiniteStateMachineTest {

  private FSMFactory fsmFactory = new FSMFactory();
  private FiniteStateMachine fsm;


  @Before
  public void setup() {
    fsm = fsmFactory.buildFSM();
  }

  @Test
  public void happyPath() throws FiniteStateMachineException {
    Assert.assertEquals("start", fsm.getCurrentState().getName());
    fsm.fire(new CrossUpperKeltnerChannel());
    Assert.assertEquals("startBuyOrderPlaced", fsm.getCurrentState().getName());
    fsm.fire(new Always());
    Assert.assertEquals("start", fsm.getCurrentState().getName());
    fsm.fire(new CrossLowerKeltnerChannel());
    Assert.assertEquals("startSellOrderPlaced", fsm.getCurrentState().getName());
    fsm.fire(new Always());
    Assert.assertEquals("start", fsm.getCurrentState().getName());
    fsm.fire(new ShortFilled());
    Assert.assertEquals("shortFilled", fsm.getCurrentState().getName());
    fsm.fire(new CrossUpperKeltnerChannel());
    Assert.assertEquals("buyOrderPlaced", fsm.getCurrentState().getName());
    fsm.fire(new LongFilled());
    Assert.assertEquals("longFilled", fsm.getCurrentState().getName());
    fsm.fire(new CrossLowerKeltnerChannel());
    Assert.assertEquals("sellOrderPlaced", fsm.getCurrentState().getName());
    fsm.fire(new CancelSCond());
    Assert.assertEquals("cancelSellOrder", fsm.getCurrentState().getName());
    fsm.fire(new Always());
    Assert.assertEquals("longFilled", fsm.getCurrentState().getName());
  }

}
