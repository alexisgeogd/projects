package gr.aueb.cf.projects;

public class project02 {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = findMaxSumSubarray(arr);
        System.out.println("Maximum sum of contiguous subarray: " + maxSum);
    }

    public static int findMaxSumSubarray(int[] arr) {
        int maxCurrent = arr[0];
        int maxGlobal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }
        return maxGlobal;
    }
}
