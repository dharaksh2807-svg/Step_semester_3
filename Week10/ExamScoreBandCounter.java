package Week10;

/**
 * Problem 4: Exam Score Band Counter
 * Counts the number of scores in range [low, high] in an ascending sorted array.
 * Uses binary search (lower bound and upper bound) to achieve logarithmic time complexity.
 *
 * Complexity Analysis:
 * - Linear Scan Complexity: O(N) time. For arrays of size 10^6 and frequent queries, O(N) is too slow.
 * - Final Binary Search Solution: O(log N) time per query, O(1) auxiliary space.
 * - Edge Duplicates Handling:
 *   A standard binary search stops at any matching element. When scores repeat at the boundaries,
 *   a lower-bound binary search finds the first index where score >= low, and an upper-bound
 *   binary search finds the first index where score > high.
 *   The count of elements in [low, high] is exactly (upperBoundIndex - lowerBoundIndex).
 */
public class ExamScoreBandCounter {

    /**
     * Finds the first index where scores[i] >= target.
     * Returns scores.length if all elements are strictly less than target.
     */
    public static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * Finds the first index where scores[i] > target.
     * Returns scores.length if all elements are <= target.
     */
    public static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0 || low > high) {
            return 0;
        }

        int lowerIndex = lowerBound(scores, low);
        int upperIndex = upperBound(scores, high);

        return upperIndex - lowerIndex;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        // Sample 1: low = 42, high = 58 -> Expected: 6
        int result1 = countInBand(scores, 42, 58);
        System.out.println("Sample 1 Output: " + result1);

        // Sample 2: low = 90, high = 100 -> Expected: 0
        int result2 = countInBand(scores, 90, 100);
        System.out.println("Sample 2 Output: " + result2);
    }
}
