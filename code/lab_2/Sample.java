import java.io.File;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
      final String PATH = "...";

      countSequential(PATH);
      countConcurrent(PATH);
  }

    private static long countFiles(final String path) {
        long count = 0;
        File file = new File(path);
        final File[] files = file.listFiles();
        if(files != null) {
            for (File child : files) {
                if (child.isFile())
                    count++;
                else
                    count += countFiles(child.getPath());
            }
        }

        return count;
    }

    private static void countSequential(final String path) {
        long start = System.nanoTime();

        System.out.println(countFiles(path));

        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start)/1.0e9);
    }

    private static void countConcurrent(final String path) {
      //Your code goes here.
    }
}
