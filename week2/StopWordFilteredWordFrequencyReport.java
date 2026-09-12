package week2;
import java.util.HashMap;
import java.util.Map;

public class StopWordFilteredWordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        // Convert to lowercase
        String cleanedText = feedback.toLowerCase();

        // Remove punctuation
        cleanedText = cleanedText.replace(".", "");
        cleanedText = cleanedText.replace(",", "");

        // Split into words
        String[] words = cleanedText.split("\\s+");

        // Store word frequencies
        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            boolean isStopWord = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequency.put(
                    word,
                    frequency.getOrDefault(word, 0) + 1
                );
            }
        }

        // Sort entries by frequency in descending order
        frequency.entrySet()
                 .stream()
                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                 .forEach(entry ->
                     System.out.println(
                         entry.getKey() + ": " + entry.getValue()
                     )
                 );
    }

    public static void main(String[] args) {

        String feedback =
            "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
    }
}