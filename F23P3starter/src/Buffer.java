import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Buffer Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-19
 */
public class Buffer {

    private int index;
    private int dirty;
    private byte[] arr;
    private RandomAccessFile file;
    //private static final int BLOCKSIZE = 4096;

    /**
     * Buffer Constuctor
     * 
     * @param index
     *            of the byte array
     * @param file
     *            being read and written
     * @throws IOException
     */
    public Buffer(int index, RandomAccessFile file) throws IOException {
        this.file = file;
        this.index = index;
        arr = new byte[4096];
        dirty = 0;
        file.seek(index);
        file.read(arr);
    }


//    /**
//     * Reads the buffer
//     * 
//     * @throws IOException
//     */
//    public void read() throws IOException {
//        file.seek(index);
//        file.read(arr);
//        //file.read(arr, index, BLOCKSIZE);
//    }


    /**
     * Writes the buffer
     * 
     * @param newArr
     *            for new byte array that will be written to old
     * @throws IOException
     */
    public void write() throws IOException {
        file.seek(index);
        file.write(arr);
    }


    /**
     * Gets the array
     * 
     * @return byte array
     */
    public byte[] getArr() {
        return arr;
    }


    /**
     * Gets the dirty byte value
     * 
     * @return the dirty byte value
     */
    public int getDirty() {
        return dirty;
    }


    /**
     * Sets the dirty byte
     * 
     * @param val
     *            for new value
     */
    public void setDirty(int val) {
        dirty = val;
    }
    
    /**
     * Gets the buffer's index value
     * 
     * return int
     *            Index
     */
    public int getIndex()
    {
        return index;
    }

}
