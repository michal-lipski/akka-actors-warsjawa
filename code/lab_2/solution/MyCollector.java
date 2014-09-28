import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;

public class MyCollector extends UntypedActor {
  int count;
  int pendingWork;

  private ActorRef exploreFiles;

  @Override
  public void preStart() {
    exploreFiles = getContext().actorOf(
        Props.create(ExploreFile.class)
            .withRouter(new RoundRobinPool(100)));
  }

  @Override
  public void onReceive(final Object message) {
    if(message instanceof String) {
      pendingWork++;
      exploreFiles.tell(message, self());
    }

    if(message instanceof Integer) {
      int filesCount = (Integer) message;
      count += filesCount;

      pendingWork--;
      if(pendingWork == 0) {
        System.out.println("Total Files: " + count);
        //getContext().system().shutdown();
      }
    }
  }
}
