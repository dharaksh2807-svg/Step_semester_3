/*A3. 3Sum
Scenario
A budgeting tool needs to find every distinct combination of exactly three transactions in a student's account history
that cancel each other out exactly — summing to zero — without ever reporting the same combination of amounts
twice, even if it could be picked out of the list in more than one way.
Task
• Accept an integer array nums.
• Return all unique triplets [nums[i], nums[j], nums[k]] (i, j, k all different positions) such that the three values
sum to exactly 0.
• Sort the array first, then for each element, use two pointers moving inward from both ends of the remaining
subarray to find pairs that complete the sum to zero — carefully skipping over duplicate values at every level to
avoid reporting the same triplet more than once.
Suggested Method Signature(s)
int[][] threeSum(int[] nums)
Sample Input / Output
Input Output
nums = [-1, 0, 1, 2, -1, -4] [[-1, -1, 2], [-1, 0, 1]]
nums = [0, 0, 0] [[0, 0, 0]] (only reported once, despite three identical

values)

Constraints
• 3 ≤ nums.length ≤ 3000.
• Aim for O(n2) time — the sort costs O(n log n), and the two-pointer scan per element costs O(n), giving O(n2)
overall.
• Duplicate handling is the single most common source of bugs in this problem — test an input with several
repeated values before considering it done.
Concepts covered: Sorting as a setup step, the two-pointer technique on a sorted array, systematic duplicate avoidance, converting
a 2-sum idea into a 3-sum solution.
*/

package Week4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public int[][] threeSum(int[] nums) {
        // Step 1: Sort the array to enable the two-pointer approach and easily spot duplicates
        Arrays.sort(nums);
        List<int[]> resultList = new ArrayList<>();
        int n = nums.length;

        // Step 2: Iterate through the array. 
        // We only go up to n - 2 because we need at least 3 elements for a triplet.
        for (int i = 0; i < n - 2; i++) {
            
            // Skip duplicate values for the first element to avoid identical triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Set up the two pointers
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a valid triplet
                    resultList.add(new int[]{nums[i], nums[left], nums[right]});

                    // Skip duplicates for the second element (left pointer)
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element (right pointer)
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move both pointers inward to look for other potential pairs
                    left++;
                    right--;
                } 
                else if (sum < 0) {
                    // If the sum is too small, we need a larger number, so move the left pointer right
                    left++;
                } 
                else {
                    // If the sum is too large, we need a smaller number, so move the right pointer left
                    right--;
                }
            }
        }

        // Convert the dynamic list back to an int[][] to match the required signature
        return resultList.toArray(new int[0][]);
    }

    // =========================================================
    // Main Method to test sample inputs
    // =========================================================
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        int[] test1 = {-1, 0, 1, 2, -1, -4};
        int[] test2 = {0, 0, 0};

        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: [-1, 0, 1, 2, -1, -4]");
        int[][] result1 = solution.threeSum(test1);
        System.out.print("Output: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(Arrays.toString(result1[i]) + (i < result1.length - 1 ? ", " : ""));
        }
        System.out.println("]\n");

        System.out.println("--- Test Case 2 ---");
        System.out.println("Input: [0, 0, 0]");
        int[][] result2 = solution.threeSum(test2);
        System.out.print("Output: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(Arrays.toString(result2[i]) + (i < result2.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}