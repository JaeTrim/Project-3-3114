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
        read();
    }


    /**
     * Reads the buffer
     * 
     * @throws IOException
     */
    public void read() throws IOException {
        file.seek(index);
        file.read(arr);
    }


    /**
     * Writes the buffer
     * 
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
     * @return index position
     */
    public int getIndex() {
        return index;
    }

}
