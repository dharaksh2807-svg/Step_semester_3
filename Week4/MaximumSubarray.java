/*
A2. Maximum Subarray
Scenario
A trader has a full year of daily profit-or-loss figures, some positive, some negative, and wants to know the single best
contiguous stretch of days to have been actively trading — the run of consecutive days whose combined total is the
highest possible.
Task
• Accept an integer array nums, which may contain negative numbers.
• Find the contiguous subarray (containing at least one number) with the largest possible sum, and return that
sum.
• Solve it using Kadane's algorithm: at each element, decide whether to extend the current running subarray
or abandon it and start fresh from the current element, based on whichever gives a larger sum.
Suggested Method Signature(s)
int maxSubArray(int[] nums)

LeetCode Practice · Functions & Arrays · Category C · Page 1 of 4

CodInClub
Powered by BridgeLabz

Sample Input / Output
Input Output
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4] 6 (the subarray [4, -1, 2, 1] sums to 6)
nums = [-3, -1, -2] -1 (all negative -- the best you can do is the single

largest value)

Constraints
• 1 ≤ nums.length ≤ 10^5.
• Solve it in O(n) time and O(1) extra space.
• Be ready to explain the O(n log n) divide-and-conquer alternative as a follow-up — interviewers often ask for
it after Kadane's.
Concepts covered: Kadane's algorithm, the “extend vs. restart” decision at each step, running-sum reset logic, handling an
all-negative array correctly.    */

package Week4;
public class MaximumSubarray {

    // =========================================================
    // Approach 1: Kadane's Algorithm
    // Time Complexity: O(N) | Space Complexity: O(1)
    // =========================================================
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // =========================================================
    // Approach 2: Divide and Conquer
    // Time Complexity: O(N log N) | Space Complexity: O(log N)
    // =========================================================
    public int maxSubArrayDivideAndConquer(int[] nums) {
        return findMaxSubArray(nums, 0, nums.length - 1);
    }

    private int findMaxSubArray(int[] nums, int left, int right) {
        // Base case: only one element
        if (left == right) {
            return nums[left]; 
        }

        int mid = left + (right - left) / 2;

        // 1. Find max in the left half
        int leftSum = findMaxSubArray(nums, left, mid);
        
        // 2. Find max in the right half
        int rightSum = findMaxSubArray(nums, mid + 1, right);
        
        // 3. Find max crossing the midpoint
        int crossSum = findMaxCrossingSubArray(nums, left, mid, right);

        // Return the maximum of the three
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int findMaxCrossingSubArray(int[] nums, int left, int mid, int right) {
        // Calculate max sum extending out to the left from mid
        int leftSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = mid; i >= left; i--) {
            currentSum += nums[i];
            leftSum = Math.max(leftSum, currentSum);
        }

        // Calculate max sum extending out to the right from mid + 1
        int rightSum = Integer.MIN_VALUE;
        currentSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currentSum += nums[i];
            rightSum = Math.max(rightSum, currentSum);
        }

        // The crossing sum is the sum of both parts
        return leftSum + rightSum;
    }


    // Main Method to test sample inputs
    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();

        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] test2 = {-3, -1, -2};

        System.out.println("--- Test Case 1 ---");
        System.out.println("Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Kadane's Result: " + solution.maxSubArray(test1)); // Expected: 6
        System.out.println("Divide & Conquer Result: " + solution.maxSubArrayDivideAndConquer(test1));

        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Array: [-3, -1, -2]");
        System.out.println("Kadane's Result: " + solution.maxSubArray(test2)); // Expected: -1
        System.out.println("Divide & Conquer Result: " + solution.maxSubArrayDivideAndConquer(test2));
    }
}