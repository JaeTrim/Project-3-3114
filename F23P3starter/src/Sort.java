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


    public static void quicksort(Comparable[] A, int i, int j) { // Quicksort
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        Swap.swap(A, pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i, j - 1, A[j]);
        Swap.swap(A, k, j); // Put pivot in place
        if ((k - i) > 1) {
            quicksort(A, i, k - 1);
        } // Sort left partition
        if ((j - k) > 1) {
            quicksort(A, k + 1, j);
        } // Sort right partition
    }


    public static int findpivot(Comparable[] A, int i, int j) {
        return (i + j) / 2;
    }


    public static int partition(
        Comparable[] A,
        int left,
        int right,
        Comparable pivot) {
        while (left <= right) { // Move bounds inward until they meet
            while (A[left].compareTo(pivot) < 0) {
                left++;
            }
            while ((right >= left) && (A[right].compareTo(pivot) >= 0)) {
                right--;
            }
            if (right > left) {
                Swap.swap(A, left, right);
            } // Swap out-of-place values
        }
        return left; // Return first position in right partition
    }
}
