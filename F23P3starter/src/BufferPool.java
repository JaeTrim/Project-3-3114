import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class BufferPool {

    private Buffer[] arr;

    public BufferPool(RandomAccessFile file, int numOfBuffers)
        throws FileNotFoundException {
        arr = new Buffer[numOfBuffers];

        
    }
    
    

}
