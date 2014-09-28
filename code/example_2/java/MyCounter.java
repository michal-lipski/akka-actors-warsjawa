import clojure.lang.LockingTransaction;
import clojure.lang.Ref;

import java.util.concurrent.Callable;

public class MyCounter {
  private Ref count;

  public MyCounter() {
    count = new Ref(0);
  }

  public int getCount() {
    return (Integer) count.deref();
  }

  public void increase(final int value) throws Exception {
    LockingTransaction.runInTransaction(
        new Callable<Void>() {
          @Override
          public Void call() throws Exception {
            System.out.println("About to increase the value by " + value);
            Thread.sleep(100);
            count.set(getCount() + value);
            return null;
          }
        });
  }
}
