import java.math.BigInteger;
import java.nio.ByteBuffer;

class AffableThread extends Thread
{
    private byte[] fileImage;
    private long min = Long.MAX_VALUE;
    public long getMin() {
        return min;
    }
    private long max = Long.MIN_VALUE;
    public long getMax() {
        return max;
    }
    private BigInteger sum = new BigInteger("0");
    public BigInteger getSum() {
        return sum;
    }
    AffableThread(byte[] fileImage){
        this.fileImage = fileImage;
    }
    @Override
    public void run()
    {
        ByteBuffer bb = ByteBuffer.wrap(this.fileImage);
        int size = bb.remaining() / 4;
        for (int i = 0; i < size; i++) {
            long temp = (long) bb.getInt() & 0xffffffffL;
            sum = sum.add(new BigInteger(String.valueOf(temp)));
            min = Math.min(min, temp);
            max = Math.max(max, temp);
        }
        System.out.println(this.getName() + " ready");
    }
}
