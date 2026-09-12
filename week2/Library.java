package week2;
public class Library {

    static String normalizeCode(String raw) {

        raw = raw.trim();

        String publisherCode = raw.substring(0, 3).toUpperCase();
        String remaining = raw.substring(3);

        return publisherCode + remaining;
    }

    static String validateAndFormat(String code) {

        // Check length
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Build formatted output
        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(code.substring(0, 3));
        result.append("] YEAR: ");
        result.append(code.substring(3, 7));
        result.append(" | CATALOG: ");
        result.append(code.substring(7, 13));

        return result.toString();
    }

    public static void main(String[] args) {

        String rawCode = " pen2026004251 ";

        String normalizedCode = normalizeCode(rawCode);

        String result = validateAndFormat(normalizedCode);

        System.out.println(result);
    }
}
