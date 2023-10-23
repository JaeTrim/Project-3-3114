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
    private int blockSize;

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
        blockSize = 4096;
// Sort sorter = new Sort((int)(file.length() / 4));
// for (int i = 0; i < numOfBuffers; i++) {
// arr[i] = new Buffer(i * 4096, file);
// }
    }


    public void swap(int i, int j) throws IOException {
        // record index
//        byte[] tempArray = new byte[4];
//        
//        //Buffer iBuf = getBuffer(i);
//        int iindex = i * 4 - ((i / (blockSize / 4)) * blockSize);
//        System.arraycopy(getBuffer(i).getArr(), iindex, tempArray, 0, 4);
//        
//        
//        //Buffer jBuf = getBuffer(j);
//        int jindex = j * 4 - ((j / (blockSize / 4)) * blockSize);
//        System.arraycopy(getBuffer(j).getArr(), jindex, getBuffer(i).getArr(), iindex, 4);
//        getBuffer(i).setDirty(1);
//        
//        System.arraycopy(tempArray, 0, getBuffer(j).getArr(), jindex, 4);
//        getBuffer(j).setDirty(1);
        
        Buffer iBuf = getBuffer(i);
        Buffer jBuf = getBuffer(j);
        int iindex = i * 4 - iBuf.getIndex();
        int jindex = j * 4 - jBuf.getIndex();
        byte[] iArr = iBuf.getArr();
        //byte[] jArr = jBuf.getArr();
        byte[] tempArray = new byte[4];

        System.arraycopy(iArr, iindex, tempArray, 0, 4);
        System.arraycopy(jBuf.getArr(), jindex, iArr, iindex, 4);
        System.arraycopy(tempArray, 0, jBuf.getArr(), jindex, 4);
        jBuf.setDirty(1);
        
        iBuf = getBuffer(i);
        iBuf.setDirty(1);
        iBuf.setArr(iArr);
        
        //jBuf = getBuffer(j);
        //jBuf.setDirty(1);
        //jBuf.setArr(jArr);
    }


    public Short getShort(int index) throws IOException {
        Buffer buf = getBuffer(index);
        int arrIndex = index * 4 - buf.getIndex();
        Short res = ByteBuffer.wrap(buf.getArr(), arrIndex,
            2).getShort();
        return res;
    }


    public void write() throws IOException {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getDirty() == 1) {
                arr[i].write();
            }
        }
    }

    private Buffer getBuffer(int index) throws IOException {
        int block = (index / (blockSize / 4)) * blockSize;
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
