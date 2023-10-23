import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Sort Class that Sorts the Information
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-17
 */

public class Sort {

    // private int numOfBuffers;
    private BufferPool pool;

    /**
     * Sort Constructor
     * 
     * @throws IOException
     */
    public Sort(RandomAccessFile file, int numOfBuffers) throws IOException {
        // this.numOfBuffers = numOfBuffers;
        pool = new BufferPool(file, numOfBuffers);
        quicksort(0, (int)((file.length() / 4) - 1));
        pool.write();
    }


    /**
     * Quicksort function
     * 
     * @param i
     *            first index
     * @param j
     *            second index
     * @throws IOException
     */
    public void quicksort(int i, int j) throws IOException {
        if (i < j) {
            int pivot = partition(i, j);
            quicksort(i, pivot - 1);
            quicksort(pivot + 1, j);
        }
    }


    /**
     * Partition Function
     * 
     * @param left
     *            is left partition from pivot
     * @param right
     *            is right partition from pivot
     * @return the first position in the right partition
     * @throws IOException
     */
    public int partition(int i, int j) throws IOException {
        Short pivot = pool.getShort(j);
        int left = i - 1;
        int right = j;
        while (true) {
            while (pool.getShort(++left) < pivot) {          
            }
            while (right > i && pool.getShort(--right) > pivot) {               
            }
            if (left >= right)
                break;
            pool.swap(left, right);
        }
        pool.swap(left, j);
        return left;
    }

}
