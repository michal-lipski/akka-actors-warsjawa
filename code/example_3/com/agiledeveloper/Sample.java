package com.agiledeveloper;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    ActorSystem actorSystem = ActorSystem.create();

    ActorRef depp = actorSystem.actorOf(Props.create(HollywoodActor.class));
    ActorRef hanks = actorSystem.actorOf(Props.create(HollywoodActor.class));

    depp.tell("Wonka", null);
    hanks.tell("Phillips", null);
    Thread.sleep(100);
    depp.tell("Sparrow", null);
    hanks.tell("Gump", null);

    System.out.println("In main " + Thread.currentThread());

    ActorRef stuntPerson = actorSystem.actorOf(Props.create(StuntPerson.class)
                                              .withRouter(new RoundRobinPool(10)));
    for(int i = 0; i < 5; i++) {
      stuntPerson.tell("stunt " + i, null);
    }

    actorSystem.shutdown();
  }
}
