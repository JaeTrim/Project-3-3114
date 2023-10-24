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
     * @param file
     *            is file passed through that will be read and written to
     * @param numOfBuffers
     *            is the number of buffers
     * @throws IOException
     */
    public Sort(RandomAccessFile file, int numOfBuffers) throws IOException {
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
     * @param i
     *            is left partition from pivot
     * @param j
     *            is right partition from pivot
     * @return the first position in the right partition
     * @throws IOException
     */
    public int partition(int i, int j) throws IOException {
        int left = i - 1;
        int right = j;
        Short pivotValue = pool.getShort(j);
        while (true) {
            Short leftValue = pool.getShort(++left);
            while (leftValue < pivotValue) {
                leftValue = pool.getShort(++left);
            }
            Short rightValue = pool.getShort(--right);
            while (right > i && rightValue > pivotValue) {
                rightValue = pool.getShort(--right);
            }
            if (left >= right) {
                break;
            }
            pool.swap(left, right);
        }
        pool.swap(left, j);
        return left;
    }

}
