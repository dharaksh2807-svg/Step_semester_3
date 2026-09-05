/*A1. Product of Array Except Self
Scenario
A pricing engine needs, for every product in a bundle, the combined price of every OTHER product in that same
bundle — computed for all products at once, without ever dividing by the current product's own price (some prices
could legitimately be zero, e.g. a free promotional item, which would make division undefined).
Task
• Accept an integer array nums.
• Return an array answer where answer[i] is the product of every element in nums except nums[i] — without
using division anywhere in your solution.
• Solve it in O(n) time using two passes: a forward pass accumulating the running product of everything to the
left of each index, and a backward pass multiplying in the running product of everything to the right.
Suggested Method Signature(s)
int[] productExceptSelf(int[] nums)
Sample Input / Output
Input Output
nums = [1, 2, 3, 4] [24, 12, 8, 6]
nums = [-1, 1, 0, -3, 3] [0, 0, 9, 0, 0] (a single zero forces every OTHER
position to be 0 except the zero's own position)

Constraints
• 2 ≤ nums.length ≤ 10^5.
• The product of any prefix or suffix fits within a 32-bit integer.
• Division is not allowed anywhere in the solution, even to “cancel it out” for zero handling.
Concepts covered: Prefix and suffix products, two-pass array traversal, handling zero values without dividing, O(n) time with O(1)
extra space beyond the output array.
*/
package Week4;

public class pricing {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Forward pass: calculate the product of all elements to the left of each index
        answer[0] = 1; // There are no elements to the left of the first element
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Backward pass: multiply the product of all elements to the right of each index
        int rightProduct = 1; // There are no elements to the right of the last element
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        pricing solution = new pricing();

        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {-1, 1, 0, -3, 3};

        System.out.println("Input: [1, 2, 3, 4]");
        System.out.println("Output: " + java.util.Arrays.toString(solution.productExceptSelf(nums1)));

        System.out.println("Input: [-1, 1, 0, -3, 3]");
        System.out.println("Output: " + java.util.Arrays.toString(solution.productExceptSelf(nums2)));
    }
}