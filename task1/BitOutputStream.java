import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Random;

public class BitOutputStream extends FilterOutputStream {
    public BitOutputStream(OutputStream out) {
        super(out);
    }

    public void writeBits(BigInteger fileSize) throws IOException {
        long start = System.currentTimeMillis();
        Random r = new Random();
        while(fileSize.compareTo(new BigInteger("0")) == 1) {
            fileSize = fileSize.add(new BigInteger("-4"));
            //int value = (int)(Math.random() * 2147483647);
            int value = r.nextInt();
            byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
            write(bytes);
        }
        long traceTime = System.currentTimeMillis() - start;
        double minutes = (double)traceTime / 1000.0 / 60.0;
        System.out.println("File generation took " + minutes + " minutes");
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}