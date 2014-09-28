package com.agiledeveloper;

import akka.actor.UntypedActor;

public class HollywoodActor extends UntypedActor {
  @Override
  public void onReceive(final Object message) throws Exception {
    System.out.println("Playing " + message + " from thread " + Thread.currentThread());
  }
}
