package fsm;

import fsm.events.Always;
import fsm.events.CancelBCond;
import fsm.events.CancelSCond;
import fsm.events.CrossLowerKeltnerChannel;
import fsm.events.CrossUpperKeltnerChannel;
import fsm.events.LongFilled;
import fsm.events.ShortFilled;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.State;
import org.jeasy.states.api.Transition;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.jeasy.states.core.TransitionBuilder;

import java.util.Arrays;
import java.util.HashSet;

public class FSMFactory {

  private State start = new State("start");
  private State startBuyOrderPlaced = new State("startBuyOrderPlaced");
  private State startSellOrderPlaced = new State("startSellOrderPlaced");
  private State startCancelBuyOrder = new State("startPCancelBuyOrder");
  private State startCancelSellOrder = new State("startCancelSellOrder");
  private State longPosition = new State("longFilled");
  private State shortPosition = new State("shortFilled");
  private State buyOrderPlaced = new State("buyOrderPlaced");
  private State sellOrderPlaced = new State("sellOrderPlaced");
  private State cancelBuyOrder = new State("cancelBuyOrder");
  private State cancelSellOrder = new State("cancelSellOrder");
  private HashSet states;

  public FSMFactory() {
    states = new HashSet();
  }

  public FiniteStateMachine buildFSM() {
    Arrays.stream(this.getClass().getDeclaredFields()).filter(field -> field.getType().isAssignableFrom(State.class)).forEach(state -> {
      try {
        states.add(state.get(this));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });
    FiniteStateMachineBuilder fb = new FiniteStateMachineBuilder(states, start);
    HashSet s = buildTransitions();
    fb.registerTransitions(s);
    return fb.build();
  }


  private HashSet buildTransitions() {

    Transition[] a = {
        new TransitionBuilder().name("startLongSignal")
            .sourceState(start)
            .eventType(CrossUpperKeltnerChannel.class)
            .targetState(startBuyOrderPlaced)
            .build(),
        new TransitionBuilder().name("startBuyOrdPlaced")
            .sourceState(startBuyOrderPlaced)
            .eventType(Always.class)
            .targetState(start)
            .build(),
        new TransitionBuilder().name("startSellSignal")
            .sourceState(start)
            .eventType(CrossLowerKeltnerChannel.class)
            .targetState(startSellOrderPlaced)
            .build(),
        new TransitionBuilder().name("startSellOrdPlaced")
            .sourceState(startSellOrderPlaced)
            .eventType(Always.class)
            .targetState(start)
            .build(),
        new TransitionBuilder().name("startCancelBuySignal")
            .sourceState(start)
            .eventType(CancelBCond.class)
            .targetState(startCancelBuyOrder)
            .build(),
        new TransitionBuilder().name("startBuyCancelled")
            .sourceState(startCancelBuyOrder)
            .eventType(Always.class)
            .targetState(start)
            .build(),
        new TransitionBuilder().name("startCancelSellSignal")
            .sourceState(start)
            .eventType(CancelSCond.class)
            .targetState(startCancelSellOrder)
            .build(),
        new TransitionBuilder().name("startCancelSellSignal")
            .sourceState(startCancelSellOrder)
            .eventType(Always.class)
            .targetState(start)
            .build(),
        new TransitionBuilder().name("startLongFilled")
            .sourceState(start)
            .eventType(LongFilled.class)
            .targetState(longPosition)
            .build(),
        new TransitionBuilder().name("startShortFilled")
            .sourceState(start)
            .eventType(ShortFilled.class)
            .targetState(shortPosition)
            .build(),
        new TransitionBuilder().name("placeSellOrd")
            .sourceState(longPosition)
            .eventType(CrossLowerKeltnerChannel.class)
            .targetState(sellOrderPlaced)
            .build(),
        new TransitionBuilder().name("cancelSellSignal")
            .sourceState(sellOrderPlaced)
            .eventType(CancelSCond.class)
            .targetState(cancelSellOrder)
            .build(),
        new TransitionBuilder().name("sellCancelled")
            .sourceState(cancelSellOrder)
            .eventType(Always.class)
            .targetState(longPosition)
            .build(),
        new TransitionBuilder().name("shortFilled")
            .sourceState(sellOrderPlaced)
            .eventType(ShortFilled.class)
            .targetState(shortPosition)
            .build(),
        new TransitionBuilder().name("placeBuyOrder")
            .sourceState(shortPosition)
            .eventType(CrossUpperKeltnerChannel.class)
            .targetState(buyOrderPlaced)
            .build(),
        new TransitionBuilder().name("cancelBuySignal")
            .sourceState(buyOrderPlaced)
            .eventType(CrossLowerKeltnerChannel.class)
            .targetState(cancelBuyOrder)
            .build(),
        new TransitionBuilder().name("buyCancelled")
            .sourceState(cancelBuyOrder)
            .eventType(Always.class)
            .targetState(shortPosition)
            .build(),
        new TransitionBuilder().name("longFilled")
            .sourceState(buyOrderPlaced)
            .eventType(LongFilled.class)
            .targetState(longPosition)
            .build()};

    return new HashSet(Arrays.asList(a));
  }
}
