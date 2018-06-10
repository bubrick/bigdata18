import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean generate_file = false; // = true; - to generate file
        if (generate_file) {
            FileOutputStream os = new FileOutputStream("file1.txt");
            BitOutputStream bos = new BitOutputStream(os);
            bos.writeBits(new BigInteger("2147483648"));
        }
        else {
            FileInputStream inputStream = new FileInputStream("file1.txt");
            long startTime = System.currentTimeMillis();

            byte bufferImage[] = new byte[inputStream.available() / 4];
            inputStream.read(bufferImage);
            AffableThread thread0 = new AffableThread(bufferImage);

            bufferImage = new byte[inputStream.available() / 4];
            inputStream.read(bufferImage);
            AffableThread thread1 = new AffableThread(bufferImage);

            bufferImage = new byte[inputStream.available() / 4];
            inputStream.read(bufferImage);
            AffableThread thread2 = new AffableThread(bufferImage);

            bufferImage = new byte[inputStream.available() / 4];
            inputStream.read(bufferImage);
            AffableThread thread3 = new AffableThread(bufferImage);

            thread0.start();
            thread1.start();
            thread2.start();
            thread3.start();

            thread0.join();
            thread1.join();
            thread2.join();
            thread3.join();

            long[] maxs = {thread0.getMax(), thread1.getMax(), thread2.getMax(), thread3.getMax()};
            Arrays.sort(maxs);
            System.out.println("Max: " + maxs[3]);

            long[] mins = {thread0.getMin(), thread1.getMin(), thread2.getMin(), thread3.getMin()};
            Arrays.sort(mins);
            System.out.println("Min: " + mins[0]);

            BigInteger sum = thread0.getSum().add(thread1.getSum()).add(thread2.getSum()).add(thread3.getSum());
            System.out.println("Sum: " + sum);

            long finishTime = System.currentTimeMillis();
            System.out.println("Completion time = " + (finishTime - startTime) + "ms");
        }
    }
}
