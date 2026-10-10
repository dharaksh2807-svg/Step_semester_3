package Week10;

import java.util.Arrays;

/**
 * Problem 8: Duty Roster Rotation
 * Rotates duty roster array to the right by k positions using modular arithmetic.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) where N is the length of the roster.
 *   Each element is placed in its new position in a single pass.
 *   Why O(K * N) shifting 1-by-1 is slow: With K up to 10^9 and N up to 100,
 *   shifting one by one would require up to 10^11 operations, leading to timeouts.
 *   Using (i + k) % N reduces the work to O(N) regardless of how large K is.
 * - Space Complexity: O(N) to construct the rotated array (or O(1) auxiliary space).
 */
public class DutyRosterRotation {

    public static String[] rotateRoster(String[] names, long k) {
        if (names == null || names.length == 0) {
            return new String[0];
        }

        int n = names.length;
        int shift = (int) (k % n);
        if (shift < 0) {
            shift += n;
        }

        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            int newIndex = (i + shift) % n;
            rotated[newIndex] = names[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        // Sample 1: names = [A, B, C, D, E], k = 2
        String[] names1 = {"A", "B", "C", "D", "E"};
        String[] result1 = rotateRoster(names1, 2);
        System.out.println("Sample 1 Output: " + Arrays.toString(result1));

        // Sample 2: names = [A, B, C, D, E], k = 7
        String[] names2 = {"A", "B", "C", "D", "E"};
        String[] result2 = rotateRoster(names2, 7);
        System.out.println("Sample 2 Output: " + Arrays.toString(result2));
    }
}
