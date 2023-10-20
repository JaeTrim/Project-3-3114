import java.io.FileNotFoundException;
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
     * @throws FileNotFoundException
     */
    public BufferPool(RandomAccessFile file, int numOfBuffers)
        throws FileNotFoundException {
        arr = new Buffer[numOfBuffers];

    }

}
