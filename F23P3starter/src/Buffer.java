import java.io.IOException;
import java.io.RandomAccessFile;

public class Buffer {

    private int index;
    private byte dirty;
    private byte[] arr;

    public Buffer(int index, RandomAccessFile file) throws IOException {
        this.index = index;
        arr = new byte[4096];
        read(file);
    }


    public void read(RandomAccessFile file) throws IOException {
        file.seek(index);
        
        file.read(arr, index, 4096);
        

    }
    
    public void write() {
        
    }


    public byte getDirty() {
        return dirty;
    }


    public void setDirty(byte val) {
        dirty = val;
    }

}
