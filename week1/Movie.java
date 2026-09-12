package week1;
public class Movie {

    static void classifyWordLengths(String review) {

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        // Split review into words
        String[] words = review.split("\\s+");

        // Check length of each word
        for (int i = 0; i < words.length; i++) {

            int length = words[i].length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } 
            else if (length >= 5 && length <= 8) {
                mediumCount++;
            } 
            else {
                longCount++;
            }
        }

        // Print results
        System.out.println("Short: " + shortCount);
        System.out.println("Medium: " + mediumCount);
        System.out.println("Long: " + longCount);
    }

    public static void main(String[] args) {

        String review = "This movie was absolutely fantastic and thrilling";

        classifyWordLengths(review);
    }
}
