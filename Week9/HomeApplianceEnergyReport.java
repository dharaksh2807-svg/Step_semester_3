package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 5: Home Appliance Energy Report
 * Models power consumption and validates optional saver mode support using interfaces.
 */
public class HomeApplianceEnergyReport {

    public interface SaverModeSupported {
        default double getSaverMultiplier() {
            return 0.75; // 25% reduction
        }
    }

    public static abstract class Appliance {
        private String name;
        private double powerWatts;
        private double hours;
        private boolean saverRequested;

        public Appliance(String name, double powerWatts, double hours, boolean saverRequested) {
            this.name = name;
            this.powerWatts = powerWatts;
            this.hours = hours;
            this.saverRequested = saverRequested;
        }

        public String getName() {
            return name;
        }

        public double getPowerWatts() {
            return powerWatts;
        }

        public double getHours() {
            return hours;
        }

        public boolean isSaverRequested() {
            return saverRequested;
        }

        public boolean isConfigurationValid() {
            if (saverRequested) {
                return this instanceof SaverModeSupported;
            }
            return true;
        }

        public double calculateUnits() {
            double units = (powerWatts * hours) / 1000.0;
            if (saverRequested && this instanceof SaverModeSupported) {
                units *= ((SaverModeSupported) this).getSaverMultiplier();
            }
            return units;
        }

        public double calculateCost() {
            return calculateUnits() * 8.00;
        }
    }

    public static class FridgeAppliance extends Appliance {
        public FridgeAppliance(double hours, boolean saverRequested) {
            super("FRIDGE", 150.0, hours, saverRequested);
        }
    }

    public static class ACAppliance extends Appliance implements SaverModeSupported {
        public ACAppliance(double hours, boolean saverRequested) {
            super("AC", 1500.0, hours, saverRequested);
        }
    }

    public static class TVAppliance extends Appliance {
        public TVAppliance(double hours, boolean saverRequested) {
            super("TV", 100.0, hours, saverRequested);
        }
    }

    public static class WasherAppliance extends Appliance implements SaverModeSupported {
        public WasherAppliance(double hours, boolean saverRequested) {
            super("WASHER", 500.0, hours, saverRequested);
        }
    }

    public static Appliance createAppliance(String type, double hours, boolean saver) {
        switch (type.trim().toUpperCase()) {
            case "FRIDGE":
                return new FridgeAppliance(hours, saver);
            case "AC":
                return new ACAppliance(hours, saver);
            case "TV":
                return new TVAppliance(hours, saver);
            case "WASHER":
            default:
                return new WasherAppliance(hours, saver);
        }
    }

    public static void processReport(List<Appliance> appliances) {
        double totalCost = 0.0;
        for (Appliance app : appliances) {
            if (!app.isConfigurationValid()) {
                System.out.printf("%s: saver mode not supported%n", app.getName());
            } else {
                double units = app.calculateUnits();
                double cost = app.calculateCost();
                totalCost += cost;
                System.out.printf("%s: Units=%.2f Cost=%.2f%n", app.getName(), units, cost);
            }
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<Appliance> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String itemLine = reader.readLine();
                        if (itemLine == null) break;
                        String[] parts = itemLine.trim().split("\\s+");
                        if (parts.length >= 2) {
                            String type = parts[0];
                            double hours = Double.parseDouble(parts[1]);
                            boolean saver = parts.length > 2 && "SAVER".equalsIgnoreCase(parts[2]);
                            list.add(createAppliance(type, hours, saver));
                        }
                    }
                    processReport(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<Appliance> sample = new ArrayList<>();
        sample.add(new FridgeAppliance(24, false));
        sample.add(new ACAppliance(8, true));
        sample.add(new TVAppliance(5, false));
        sample.add(new WasherAppliance(2, true));
        processReport(sample);
    }
}
