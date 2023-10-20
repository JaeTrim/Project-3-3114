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
        arr = new byte[(int)file.length()];
        read();
    }


    /**
     * Reads the buffer
     * 
     * @throws IOException
     */
    public void read() throws IOException {
        file.seek(index);
        file.read(arr, index, (int)file.length());
    }


    /**
     * Writes the buffer
     * 
     * @param newArr
     *            for new byte array that will be written to old
     * @throws IOException
     */
    public void write(byte[] newArr) throws IOException {
        file.seek(index);
        file.write(newArr);
        setDirty(1);
        arr = newArr;
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

}
