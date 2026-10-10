package Week10;

/**
 * Problem 7: Second-Best Score
 * Finds the second-highest distinct score in an unsorted array without sorting.
 *
 * Complexity Analysis:
 * - Single-pass linear solution: O(N) time and O(1) auxiliary space.
 * - Comparison with sorting:
 *   Sorting takes O(N log N) time (and potentially O(N) extra space or mutates input),
 *   whereas inspecting elements once while tracking the two largest distinct numbers
 *   solves the problem in optimal O(N) time with zero array mutations.
 */
public class SecondBestScore {

    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length < 2) {
            return -1;
        }

        int highest = -1;
        int second = -1;

        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score < highest && score > second) {
                second = score;
            }
        }

        return second;
    }

    public static void main(String[] args) {
        // Sample 1: scores = [45, 78, 92, 78, 60] -> Expected: 78
        int[] scores1 = {45, 78, 92, 78, 60};
        System.out.println("Sample 1 Output: " + secondHighest(scores1));

        // Sample 2: scores = [50, 50, 50] -> Expected: -1
        int[] scores2 = {50, 50, 50};
        System.out.println("Sample 2 Output: " + secondHighest(scores2));
    }
}
