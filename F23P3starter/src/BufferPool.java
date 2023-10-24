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
    private Buffer lastBuffer;
    private int lastIndex;

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
        lastIndex = -1;
    }


    /**
     * Swaps records in buffer
     * 
     * @param i
     *            first index
     * @param j
     *            second index
     * @throws IOException
     */
    public void swap(int i, int j) throws IOException {
        Buffer jBuf = getBuffer(j);
        int jindex = j * 4 - jBuf.getIndex();

        Buffer iBuf = getBuffer(i);
        int iindex = i * 4 - iBuf.getIndex();

        byte temp0 = iBuf.getArr()[iindex];
        byte temp1 = iBuf.getArr()[iindex + 1];

        iBuf.getArr()[iindex] = jBuf.getArr()[jindex];
        iBuf.getArr()[iindex + 1] = jBuf.getArr()[jindex + 1];
        iBuf.setDirty(1);

        jBuf = getBuffer(j);
        jBuf.getArr()[jindex] = temp0;
        jBuf.getArr()[jindex + 1] = temp1;

        jBuf.setDirty(1);
    }


    /**
     * Gets the short at an index in the Buffer
     * 
     * @param index
     *            is the index in the Buffer
     * @return the short value
     * @throws IOException
     */
    public Short getShort(int index) throws IOException {
        Buffer buf = getBuffer(index);
        int arrIndex = index * 4 - buf.getIndex();
        Short res = ByteBuffer.wrap(buf.getArr(), arrIndex, 2).getShort();
        return res;
    }


    /**
     * Writes buffers back to the file
     * 
     * @throws IOException
     */
    public void write() throws IOException {
        for (Buffer buffer : arr) {
            if (buffer != null && buffer.getDirty() == 1) {
                buffer.write();
            }
        }

    }


    /**
     * Gets a buffer from the buffer pool
     * 
     * @param index
     *            is the index of the buffer in the pool
     * @return the buffer located
     * @throws IOException
     */
    private Buffer getBuffer(int index) throws IOException {
        int block = (index / 1024) * 4096;
        if (lastIndex == block && lastBuffer != null) {
            return lastBuffer;
        }
        int blockCheck = inPool(block);
        if (blockCheck != -1) {
            lastBuffer = arr[blockCheck];
        }
        else {
            if (arr[numOfBuffers - 1] != null && arr[numOfBuffers - 1]
                .getDirty() == 1) {
                arr[numOfBuffers - 1].write();
            }
            lastBuffer = new Buffer(block, file);
            shift(numOfBuffers - 1);
            arr[0] = lastBuffer;
        }
        lastIndex = block;
        return lastBuffer;
    }


    /**
     * Checks if Buffer is associated with block in the pool
     * 
     * @param blockIndex
     *            is the index of the Buffer
     * @return the index of the buffer or -1 if no buffer is found
     */
    private int inPool(int blockIndex) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].getIndex() == blockIndex) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Makes space in the buffer pool by shifting buffers position
     * 
     * @param index
     *            is index of buffer
     */
    private void shift(int index) {
        for (int i = index - 1; i >= 0; i--) {
            Buffer temp = arr[i];
            arr[i + 1] = temp;
        }
    }
}
