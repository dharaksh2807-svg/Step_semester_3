package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 4: City Cab Fare Meter
 * Enforces shared minimum trip pricing and selectively enables night service capability via an interface.
 */
public class CityCabFareMeter {

    public interface NightServiceable {
        default double getNightSurchargeMultiplier() {
            return 1.20; // 20% surcharge
        }
    }

    public static abstract class Cab {
        public static final double MINIMUM_FARE = 100.00;

        private String cabType;
        private double distanceKm;
        private String timeOfDay; // DAY or NIGHT

        public Cab(String cabType, double distanceKm, String timeOfDay) {
            this.cabType = cabType;
            this.distanceKm = distanceKm;
            this.timeOfDay = timeOfDay;
        }

        public String getCabType() {
            return cabType;
        }

        public double getDistanceKm() {
            return distanceKm;
        }

        public boolean isNightTrip() {
            return "NIGHT".equalsIgnoreCase(timeOfDay);
        }

        public abstract double getRatePerKm();

        public boolean canFulfillTrip() {
            if (isNightTrip()) {
                return this instanceof NightServiceable;
            }
            return true;
        }

        public double calculateFare() {
            double baseFare = Math.max(MINIMUM_FARE, distanceKm * getRatePerKm());
            if (isNightTrip() && this instanceof NightServiceable) {
                baseFare *= ((NightServiceable) this).getNightSurchargeMultiplier();
            }
            return baseFare;
        }
    }

    public static class MiniCab extends Cab {
        public MiniCab(double distanceKm, String timeOfDay) {
            super("MINI", distanceKm, timeOfDay);
        }

        @Override
        public double getRatePerKm() {
            return 10.00;
        }
    }

    public static class SedanCab extends Cab implements NightServiceable {
        public SedanCab(double distanceKm, String timeOfDay) {
            super("SEDAN", distanceKm, timeOfDay);
        }

        @Override
        public double getRatePerKm() {
            return 14.00;
        }
    }

    public static class SUVCab extends Cab implements NightServiceable {
        public SUVCab(double distanceKm, String timeOfDay) {
            super("SUV", distanceKm, timeOfDay);
        }

        @Override
        public double getRatePerKm() {
            return 18.00;
        }
    }

    public static Cab createCab(String type, double distance, String timeOfDay) {
        switch (type.trim().toUpperCase()) {
            case "MINI":
                return new MiniCab(distance, timeOfDay);
            case "SEDAN":
                return new SedanCab(distance, timeOfDay);
            case "SUV":
            default:
                return new SUVCab(distance, timeOfDay);
        }
    }

    public static void processTrips(List<Cab> trips) {
        double total = 0.0;
        for (Cab cab : trips) {
            if (!cab.canFulfillTrip()) {
                System.out.printf("%s: night service not available%n", cab.getCabType());
            } else {
                double fare = cab.calculateFare();
                total += fare;
                System.out.printf("%s: %.2f%n", cab.getCabType(), fare);
            }
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
                    List<Cab> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String tripLine = reader.readLine();
                        if (tripLine == null) break;
                        String[] parts = tripLine.trim().split("\\s+");
                        if (parts.length >= 3) {
                            list.add(createCab(parts[0], Double.parseDouble(parts[1]), parts[2]));
                        }
                    }
                    processTrips(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<Cab> sample = new ArrayList<>();
        sample.add(new MiniCab(8, "DAY"));
        sample.add(new SedanCab(10, "NIGHT"));
        sample.add(new SUVCab(20, "DAY"));
        sample.add(new MiniCab(5, "NIGHT"));
        processTrips(sample);
    }
}
