package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 5: Public Transport Fare Calculator
 * Models polymorphic fare calculation across Bus, Train, and Metro transit options.
 */
public class TransportFareCalculator {

    public static abstract class TransportJourney {
        private String type;
        private double distance;

        public TransportJourney(String type, double distance) {
            this.type = type;
            this.distance = distance;
        }

        public String getType() {
            return type;
        }

        public double getDistance() {
            return distance;
        }

        public abstract double calculateFare();
    }

    public static class BusJourney extends TransportJourney {
        public BusJourney(double distance) {
            super("BUS", distance);
        }

        @Override
        public double calculateFare() {
            double fare = 2.0 + (getDistance() * 0.10);
            return Math.min(10.0, fare); // Capped at $10.00
        }
    }

    public static class TrainJourney extends TransportJourney {
        public TrainJourney(double distance) {
            super("TRAIN", distance);
        }

        @Override
        public double calculateFare() {
            return 3.0 + (getDistance() * 0.15);
        }
    }

    public static class MetroJourney extends TransportJourney {
        private double peakHourFactor;

        public MetroJourney(double distance, double peakHourFactor) {
            super("METRO", distance);
            this.peakHourFactor = peakHourFactor;
        }

        @Override
        public double calculateFare() {
            double basePlusDistance = 1.50 + (getDistance() * 0.20);
            return basePlusDistance * peakHourFactor;
        }
    }

    public static TransportJourney parseJourney(String line) {
        String[] parts = line.trim().split("\\s+");
        String type = parts[0].toUpperCase();
        double distance = Double.parseDouble(parts[1]);

        switch (type) {
            case "BUS":
                return new BusJourney(distance);
            case "TRAIN":
                return new TrainJourney(distance);
            case "METRO":
            default:
                double peak = parts.length > 2 ? Double.parseDouble(parts[2]) : 1.0;
                return new MetroJourney(distance, peak);
        }
    }

    public static void processJourneys(List<TransportJourney> journeys) {
        double total = 0.0;
        for (TransportJourney j : journeys) {
            double fare = j.calculateFare();
            total += fare;
            System.out.printf("%s: %.2f%n", j.getType(), fare);
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
                    List<TransportJourney> journeys = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String itemLine = reader.readLine();
                        if (itemLine == null) break;
                        journeys.add(parseJourney(itemLine));
                    }
                    processJourneys(journeys);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<TransportJourney> sample = new ArrayList<>();
        sample.add(new BusJourney(15));
        sample.add(new TrainJourney(50));
        sample.add(new MetroJourney(10, 1.5));
        processJourneys(sample);
    }
}
