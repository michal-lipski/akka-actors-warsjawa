package com.agiledeveloper;

import akka.actor.UntypedActor;

public class StuntPerson extends UntypedActor {
  @Override
  public void onReceive(final Object stunt) throws Exception {
    System.out.println(hashCode() + " doing ... " + stunt);
  }
}
