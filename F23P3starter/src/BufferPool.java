import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Buffer Pool Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-17
 */
public class BufferPool {

    private int numOfBuffers;
    private Buffer[] arr;
    private RandomAccessFile file;

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
        this.numOfBuffers = numOfBuffers;
        this.file = file;
        arr = new Buffer[numOfBuffers];
    }


    public void swap(int i, int j) throws IOException {
        
        Buffer jBuf = getBuffer(j);
        int jindex = j * 4 - jBuf.getIndex();
        
        Buffer iBuf = getBuffer(i);
        int iindex = i * 4 - iBuf.getIndex();
        
        //byte[] iArr = iBuf.getArr();
        
//        Buffer jBuf = getBuffer(j);
//        int jindex = j * 4 - jBuf.getIndex();
        //byte[] jArr = jBuf.getArr();
        // byte[] tempArray = new byte[4];

        // System.arraycopy(iArr, iindex, tempArray, 0, 4);
        // System.arraycopy(jArr, jindex, iArr, iindex, 4);
        // System.arraycopy(tempArray, 0, jArr, jindex, 4);
        // jBuf.setDirty(1);

        byte temp0 = iBuf.getArr()[iindex];
        byte temp1 = iBuf.getArr()[iindex + 1];
        

        iBuf.getArr()[iindex] = jBuf.getArr()[jindex];
        iBuf.getArr()[iindex + 1] = jBuf.getArr()[jindex + 1];
        iBuf.setDirty(1);
        
        //jBuf = getBuffer(j);
        jBuf.getArr()[jindex] = temp0;
        jBuf.getArr()[jindex + 1] = temp1;
       

        //iBuf.setDirty(1);
        jBuf.setDirty(1);
    }


    public Short getShort(int index) throws IOException {
        Buffer buf = getBuffer(index);
        int arrIndex = index * 4 - buf.getIndex();
        Short res = ByteBuffer.wrap(buf.getArr(), arrIndex, 2).getShort();
        return res;
    }


    public void write() throws IOException {
        for (int i = 0; i < arr.length; i++) {
            Buffer buffer = arr[i];
            if (buffer != null && buffer.getDirty() == 1) {
                buffer.write();
            }
        }
    }


    private Buffer getBuffer(int index) throws IOException {
        int block = (index / (1024)) * 4096;
        int blockCheck = inPool(block);

        if ((arr[numOfBuffers - 1] != null) && blockCheck == -1) {
            if (arr[numOfBuffers - 1].getDirty() == 1) {
                arr[numOfBuffers - 1].write();
            }

        }

        if (blockCheck == -1) {
            Buffer buf = new Buffer(block, file);
            shift(numOfBuffers - 1);
            arr[0] = buf;
        }
        else {
            Buffer temp = arr[blockCheck];
            shift(blockCheck);
            arr[0] = temp;
        }

        return arr[0];
    }


    private int inPool(int blockIndex) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].getIndex() == blockIndex) {
                return i;
            }
        }
        return -1;
    }


    private void shift(int index) {
        for (int i = index - 1; i >= 0; i--) {
            Buffer temp = arr[i];
            arr[i + 1] = temp;
        }
    }
}
