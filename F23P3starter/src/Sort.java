import java.nio.ByteBuffer;

/**
 * Sort Class that Sorts the Information
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-17
 */

public class Sort {

    /**
     * Sort Constructor
     */
    public Sort() {

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
     */
    public void quicksort(byte[] a, int i, int j) { // Quicksort
        int pivotindex = findpivot(i, j); // Pick a pivot
        swap(a, pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(a, i, j - 1, ByteBuffer.wrap(a, j * 4, 2));
        swap(a, k, j); // Put pivot in place
        if ((k - i) > 1) {
            quicksort(a, i, k - 1);
        } // Sort left partition
        if ((j - k) > 1) {
            quicksort(a, k + 1, j);
        } // Sort right partition
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
     */
    public int partition(byte[] a, int left, int right, ByteBuffer pivot) {
        while (left <= right) { // Move bounds inward until they meet
            while (ByteBuffer.wrap(a, left * 4, 2).compareTo(pivot) < 0) {
                left++;
            }
            while ((right >= left) && ByteBuffer.wrap(a, right * 4, 2)
                .compareTo(pivot) >= 0) {
                right--;
            }
            if (right > left) {
                swap(a, left, right);
            } // Swap out-of-place values
        }
        return left; // Return first position in right partition
    }


    private static void swap(byte[] a, int i, int j) {
        int iindex = i * 4;
        int jindex = j * 4;

        byte[] tempArray = new byte[4];

        System.arraycopy(a, iindex, tempArray, 0, 4);
        System.arraycopy(a, jindex, a, iindex, 4);
        System.arraycopy(tempArray, 0, a, jindex, 4);

    }

}
