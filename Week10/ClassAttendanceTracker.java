package Week10;

/**
 * Problem 6: Class Attendance Tracker
 * Computes total present days and the longest consecutive attendance streak in a single pass.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) where N is the length of the days array.
 *   Both total present count and longest streak are evaluated simultaneously in one linear pass.
 * - Space Complexity: O(1) auxiliary memory using primitive counters.
 */
public class ClassAttendanceTracker {

    public static class AttendanceResult {
        public final int presentDays;
        public final int longestStreak;

        public AttendanceResult(int presentDays, int longestStreak) {
            this.presentDays = presentDays;
            this.longestStreak = longestStreak;
        }

        @Override
        public String toString() {
            return "Present: " + presentDays + ", Longest streak: " + longestStreak;
        }
    }

    public static AttendanceResult attendanceSummary(int[] days) {
        if (days == null || days.length == 0) {
            return new AttendanceResult(0, 0);
        }

        int totalPresent = 0;
        int currentStreak = 0;
        int longestStreak = 0;

        for (int day : days) {
            if (day == 1) {
                totalPresent++;
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }

        return new AttendanceResult(totalPresent, longestStreak);
    }

    public static void main(String[] args) {
        // Sample 1: days = [1, 1, 0, 1, 1, 1, 0, 1]
        int[] days1 = {1, 1, 0, 1, 1, 1, 0, 1};
        System.out.println(attendanceSummary(days1));

        // Sample 2: days = [0, 0, 0]
        int[] days2 = {0, 0, 0};
        System.out.println(attendanceSummary(days2));
    }
}
