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
    
    //private int numOfBuffers;
    private BufferPool pool;

    /**
     * Sort Constructor
     * @throws IOException 
     */
    public Sort(RandomAccessFile file, int numOfBuffers) throws IOException {
        //this.numOfBuffers = numOfBuffers;
        pool = new BufferPool(file, numOfBuffers);
        quicksort(0, (int)((file.length() / 4) - 1));
        pool.write();
    }


    /**
     * Quicksort function
     * 
     * @param a
     *            for byte array passed through
     * @param i
     *            first index
     * @param j
     *            second index
     * @throws IOException 
     */
    public void quicksort(int i, int j) throws IOException { // Quicksort
        int pivotindex = findpivot(i, j); // Pick a pivot
        pool.swap(pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(i, j - 1, pool.getShort(j));
        pool.swap(k, j); // Put pivot in place
        if ((k - i) > 1) {
            quicksort(i, k - 1);
        } // Sort left partition
        if ((j - k) > 1) {
            quicksort(k + 1, j);

        } // Sort right partition
        
//        if (i < j)
//        {
//            int p = findpivot(i, j);
//            short pivot = pool.getShort(p, 2);
//            int left = partition(i, j, pivot);
//            int right = left;
//            //, right := partition(A, p, lo, hi)  // note: multiple return values
//            quicksort(i, left - 1);
//            quicksort(right + 1, j);
//        }
    }


    /**
     * Finds the pivot based on two indexes
     * 
     * @param i
     *            first index
     * @param j
     *            second index
     * @return the middle index
     */
    public int findpivot(int i, int j) {
        return (i + j) / 2;
    }


//    public Short getShort(byte[] a, int i, int j) {
//        Short res = ByteBuffer.wrap(a, i, j).getShort();
//        return res;
//    }


    /**
     * Partition Function
     * 
     * @param a
     *            byte array passed through
     * @param left
     *            is left partition from pivot
     * @param right
     *            is right partition from pivot
     * @param pivot
     *            is pivot value
     * @return the first position in the right partition
     * @throws IOException 
     */
    public int partition(int left, int right, Short pivot) throws IOException {
        while (left <= right) { // Move bounds inward until they meet
            while (pool.getShort(left).compareTo(pivot) < 0) {
                left++;
            }
            while ((right >= left) && pool.getShort(right).compareTo(
                pivot) >= 0) {
                right--;
            }
            if (right > left) {
                pool.swap(left, right);
            } // Swap out-of-place values
        }
        return left; // Return first position in right partition
    }


//    private static void swap(byte[] a, int i, int j) {
//        int iindex = i * 4;
//        int jindex = j * 4;
//
//        byte[] tempArray = new byte[4];
//
//        System.arraycopy(a, iindex, tempArray, 0, 4);
//        System.arraycopy(a, jindex, a, iindex, 4);
//        System.arraycopy(tempArray, 0, a, jindex, 4);
//
//    }


}
