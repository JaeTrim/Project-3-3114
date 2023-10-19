import java.nio.ByteBuffer;

/**
 * Sort Class that Sorts the Information
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 2023-10-17
 */

public class Sort {

    public Sort() {

    }


    public void quicksort(byte[] A, int i, int j) { // Quicksort
        int pivotindex = findpivot(i, j); // Pick a pivot
        swap(A, pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i, j - 1, ByteBuffer.wrap(A, j, 2));
        swap(A, k, j); // Put pivot in place
        if ((k - i) > 1) {
            quicksort(A, i, k - 1);
        } // Sort left partition
        if ((j - k) > 1) {
            quicksort(A, k + 1, j);
        } // Sort right partition
    }


    public int findpivot(int i, int j) {
        return (i + j) / 2;
    }


    public int partition(
        byte[] A,
        int left,
        int right,
        ByteBuffer pivot) {
        while (left <= right) { // Move bounds inward until they meet
            while (ByteBuffer.wrap(A, left, 2).compareTo(pivot) < 0) {
                left++;
            }
            while ((right >= left) && ByteBuffer.wrap(A, left, 2).compareTo(pivot) >= 0) {
                right--;
            }
            if (right > left) {
                swap(A, left, right);
            } // Swap out-of-place values
        }
        return left; // Return first position in right partition
    }

    
    private static void swap(byte[] A, int i, int j)
    {
        int iindex = i * 4;
        int jindex = j * 4;
        for (int n = 0; n < 4; n++)
        {
            byte temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j++;
        }
    }
    

}
