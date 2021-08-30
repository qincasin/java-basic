package tree;

/**
 * Created by qincasin on 2021/7/18.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(search(arr, 10));
    }

    public static int search(int[] arr, int data) {
        int low =0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == data) {
                return low;
            } else if (arr[mid] < data) {
                low = low + 1;
            } else {
                low = high - 1;
            }
        }
        return -1;
    }

}
