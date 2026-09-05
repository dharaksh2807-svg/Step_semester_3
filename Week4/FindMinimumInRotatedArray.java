package Week4;
/* 
A5. Find Minimum in Rotated Sorted Array
Scenario
A circular duty roster was originally sorted by join date, then “rotated” at some unknown point when the office
started the printed list from a different staff member instead of the very first one. Given only the resulting list, find the
original earliest join date — without scanning every entry one by one.
Task
• Accept an integer array nums of unique elements, originally sorted in ascending order and then rotated at
some unknown pivot.
• Return the minimum element in the array.
• Solve it using a modified binary search rather than a linear scan: at each step, compare the middle element
to the rightmost element to decide which half of the array the minimum must be hiding in.
Suggested Method Signature(s)
int findMin(int[] nums)
Sample Input / Output

LeetCode Practice · Functions & Arrays · Category C · Page 3 of 4

CodInClub
Powered by BridgeLabz
Input Output
nums = [3, 4, 5, 1, 2] 1
nums = [4, 5, 6, 7, 0, 1, 2] 0
nums = [11, 13, 15, 17] 11 (no rotation actually occurred)
Constraints
• 1 ≤ nums.length ≤ 5000.
• All elements are distinct.
• Solve it in O(log n) time — a full linear scan will be correct but will not earn full marks.
Concepts covered: Binary search adapted to a rotated (not fully sorted) array, deciding which half genuinely contains the answer,
correctly handling the already-sorted (no-rotation) case as well.

*/
public class FindMinimumInRotatedArray {

    // =========================================================
    // Approach: Modified Binary Search
    // Time Complexity: O(log N) | Space Complexity: O(1)
    // =========================================================
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Loop runs until left and right pointers converge
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare the middle element with the rightmost element
            if (nums[mid] > nums[right]) {
                // If mid is greater than the rightmost element, 
                // the rotation pivot (minimum element) MUST be to the right of mid.
                left = mid + 1;
            } else {
                // If mid is less than or equal to the rightmost element,
                // the right half is properly sorted. The minimum is either 
                // mid itself or in the left half.
                right = mid;
            }
        }

        // When left == right, we have found the minimum element
        return nums[left];
    }

    // =========================================================
    // Main Method to test sample inputs
    // =========================================================
    public static void main(String[] args) {
        FindMinimumInRotatedArray solution = new FindMinimumInRotatedArray();

        int[] test1 = {3, 4, 5, 1, 2};
        int[] test2 = {4, 5, 6, 7, 0, 1, 2};
        int[] test3 = {11, 13, 15, 17};

        System.out.println("--- Test Case 1 ---");
        System.out.println("Array: [3, 4, 5, 1, 2]");
        System.out.println("Output: " + solution.findMin(test1)); // Expected: 1

        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Output: " + solution.findMin(test2)); // Expected: 0

        System.out.println("\n--- Test Case 3 --- (No rotation)");
        System.out.println("Array: [11, 13, 15, 17]");
        System.out.println("Output: " + solution.findMin(test3)); // Expected: 11
    }
}