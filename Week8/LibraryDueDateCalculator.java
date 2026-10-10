package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 2: Library Item Due Date Calculator
 * Calculates due dates for Books, DVDs, and Magazines using polymorphic duration logic.
 */
public class LibraryDueDateCalculator {

    public static final LocalDate BASE_DATE = LocalDate.of(2023, 10, 26);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static abstract class LibraryItem {
        private String title;

        public LibraryItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public abstract int getBorrowDurationDays();

        public LocalDate calculateDueDate(LocalDate fromDate) {
            return fromDate.plusDays(getBorrowDurationDays());
        }
    }

    public static class BookItem extends LibraryItem {
        public BookItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowDurationDays() {
            return 14; // Standard book loan: 14 days
        }
    }

    public static class DVDItem extends LibraryItem {
        public DVDItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowDurationDays() {
            return 7; // DVD loan: 7 days
        }
    }

    public static class MagazineItem extends LibraryItem {
        public MagazineItem(String title) {
            super(title);
        }

        @Override
        public int getBorrowDurationDays() {
            return 3; // Magazine loan: 3 days
        }
    }

    public static LibraryItem parseItem(String line) {
        line = line.trim();
        String type;
        String title;

        int firstSpace = line.indexOf(' ');
        if (firstSpace == -1) {
            type = line;
            title = "";
        } else {
            type = line.substring(0, firstSpace).trim();
            title = line.substring(firstSpace + 1).trim();
            if (title.startsWith("\"") && title.endsWith("\"") && title.length() >= 2) {
                title = title.substring(1, title.length() - 1);
            }
        }

        switch (type.toUpperCase()) {
            case "BOOK":
                return new BookItem(title);
            case "DVD":
                return new DVDItem(title);
            case "MAGAZINE":
            default:
                return new MagazineItem(title);
        }
    }

    public static void displayDueDates(List<LibraryItem> items, LocalDate currentDate) {
        for (LibraryItem item : items) {
            LocalDate dueDate = item.calculateDueDate(currentDate);
            System.out.println(item.getTitle() + ": " + dueDate.format(DATE_FORMATTER));
        }
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<LibraryItem> items = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String itemLine = reader.readLine();
                        if (itemLine == null) break;
                        items.add(parseItem(itemLine));
                    }
                    displayDueDates(items, BASE_DATE);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<LibraryItem> sample = new ArrayList<>();
        sample.add(new BookItem("1984"));
        sample.add(new DVDItem("The Matrix"));
        sample.add(new MagazineItem("Forbes Issue 500"));
        displayDueDates(sample, BASE_DATE);
    }
}
