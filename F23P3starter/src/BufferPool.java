import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Buffer Pool Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-17
 */
public class BufferPool {

    private Buffer[] arr;

    /**
     * Buffer Pool Constructor
     * 
     * @param file
     *            for input file
     * @param numOfBuffers
     *            is num of buffers
     * @throws IOException 
     */
    public BufferPool(RandomAccessFile file, int numOfBuffers)
        throws IOException {
        arr = new Buffer[numOfBuffers];
        for (int i = 0; i < numOfBuffers; i++) {
            arr[i] = new Buffer(i * 4096, file);
        }

    }
    
    public void read() {
        
    }

}
