package Week4;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // =========================================================
    // Approach: Prefix Sum with Hash Map
    // Time Complexity: O(N) | Space Complexity: O(N)
    // =========================================================
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        
        // Map to store the frequency of each prefix sum seen so far
        // Key: Prefix Sum -> Value: Frequency
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: A prefix sum of 0 has occurred exactly 1 time (the "empty" prefix).
        // This is crucial for subarrays that start from index 0 and exactly equal k.
        prefixSumMap.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            // Check if there is a previously seen prefix sum that, when subtracted 
            // from the current running sum, leaves exactly 'k'.
            int requiredPrefix = currentSum - k;
            
            if (prefixSumMap.containsKey(requiredPrefix)) {
                // Add the number of times this required prefix sum has occurred
                count += prefixSumMap.get(requiredPrefix);
            }
            
            // Add the current prefix sum to the map for future iterations
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }

    // =========================================================
    // Main Method to test sample inputs
    // =========================================================
    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();

        int[] test1 = {1, 1, 1};
        int k1 = 2;
        
        int[] test2 = {1, -1, 0};
        int k2 = 0;

        System.out.println("--- Test Case 1 ---");
        System.out.println("Array: [1, 1, 1], k = 2");
        System.out.println("Output: " + solution.subarraySum(test1, k1)); // Expected: 2

        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Array: [1, -1, 0], k = 0");
        System.out.println("Output: " + solution.subarraySum(test2, k2)); // Expected: 3
    }
}