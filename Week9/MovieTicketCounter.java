package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 1: Movie Ticket Counter
 * Encapsulates ticket pricing where the shared convenience fee is defined once in the base class.
 */
public class MovieTicketCounter {

    public static abstract class Ticket {
        public static final double CONVENIENCE_FEE = 20.00;

        private String seatType;
        private int count;

        public Ticket(String seatType, int count) {
            this.seatType = seatType;
            this.count = count;
        }

        public String getSeatType() {
            return seatType;
        }

        public int getCount() {
            return count;
        }

        public abstract double getBasePricePerTicket();

        public double calculateAmount() {
            return (getBasePricePerTicket() + CONVENIENCE_FEE) * count;
        }
    }

    public static class RegularTicket extends Ticket {
        public RegularTicket(int count) {
            super("REGULAR", count);
        }

        @Override
        public double getBasePricePerTicket() {
            return 150.00;
        }
    }

    public static class PremiumTicket extends Ticket {
        public PremiumTicket(int count) {
            super("PREMIUM", count);
        }

        @Override
        public double getBasePricePerTicket() {
            return 250.00;
        }
    }

    public static class ReclinerTicket extends Ticket {
        public ReclinerTicket(int count) {
            super("RECLINER", count);
        }

        @Override
        public double getBasePricePerTicket() {
            return 400.00;
        }
    }

    public static Ticket createTicket(String seatType, int count) {
        switch (seatType.trim().toUpperCase()) {
            case "REGULAR":
                return new RegularTicket(count);
            case "PREMIUM":
                return new PremiumTicket(count);
            case "RECLINER":
            default:
                return new ReclinerTicket(count);
        }
    }

    public static void processBookings(List<Ticket> tickets) {
        double total = 0.0;
        for (Ticket t : tickets) {
            double amount = t.calculateAmount();
            total += amount;
            System.out.printf("%s: %.2f%n", t.getSeatType(), amount);
        }
        System.out.printf("Total: %.2f%n", total);
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<Ticket> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String bookingLine = reader.readLine();
                        if (bookingLine == null) break;
                        String[] parts = bookingLine.trim().split("\\s+");
                        if (parts.length >= 2) {
                            list.add(createTicket(parts[0], Integer.parseInt(parts[1])));
                        }
                    }
                    processBookings(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<Ticket> sample = new ArrayList<>();
        sample.add(new RegularTicket(3));
        sample.add(new PremiumTicket(2));
        sample.add(new ReclinerTicket(1));
        processBookings(sample);
    }
}
