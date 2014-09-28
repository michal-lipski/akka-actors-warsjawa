import com.agiledeveloper.MyCounter;

public class Sample {
  public static void main(String[] args) throws Exception {
    final MyCounter myCounter = new MyCounter();

    System.out.println(myCounter.getCount());

    myCounter.increase(100);

    Thread.sleep(1000);
    System.out.println(myCounter.getCount());

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          myCounter.increase(100);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          myCounter.increase(200);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();

    Thread.sleep(1000);
    System.out.println(myCounter.getCount());
  }
}
