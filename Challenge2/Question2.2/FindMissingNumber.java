import java.util.Arrays;

public class FindMissingNumber {
    public static void main(String[] args) {
        int[] arr = {3, 7, 1, 2, 6, 4};
        int n = arr.length;

        //Solution 1
        System.out.println("Solution 1 answer: " + findMissingNumber1(arr, n));
        //Solution 2
        System.out.println("Solution 2 answer: " + findMissingNumber2(arr, n));
    }
    public static int findMissingNumber1(int[] arr, int n) {
        Arrays.sort(arr);
        for(int i = 1; i < n - 1; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                return arr[i - 1] + 1;
            }
        }
        return 0;
    }
    public static int findMissingNumber2(int[] arr, int n) {
        int total = (n + 1) * (n + 2) / 2;
        int sum = 0;
        for(int num : arr) {
            sum += num;
        }
        return total - sum;
    }
}
