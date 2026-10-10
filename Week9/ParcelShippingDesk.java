package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 2: Parcel Shipping Desk
 * Models parcel shipping charges and separates optional insurance capability via an interface.
 */
public class ParcelShippingDesk {

    public interface Insurable {
        double calculateInsurance();
    }

    public static abstract class Parcel {
        private String type;
        private double weightKg;
        private double declaredValue;

        public Parcel(String type, double weightKg, double declaredValue) {
            this.type = type;
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        public String getType() {
            return type;
        }

        public double getWeightKg() {
            return weightKg;
        }

        public double getDeclaredValue() {
            return declaredValue;
        }

        public abstract double calculateShippingCharge();

        public double getInsuranceAmount() {
            if (this instanceof Insurable) {
                return ((Insurable) this).calculateInsurance();
            }
            return 0.00;
        }

        public double calculateTotal() {
            return calculateShippingCharge() + getInsuranceAmount();
        }
    }

    public static class StandardParcel extends Parcel {
        public StandardParcel(double weightKg, double declaredValue) {
            super("STANDARD", weightKg, declaredValue);
        }

        @Override
        public double calculateShippingCharge() {
            return 40.00 + (10.00 * getWeightKg());
        }
    }

    public static class ExpressParcel extends Parcel implements Insurable {
        public ExpressParcel(double weightKg, double declaredValue) {
            super("EXPRESS", weightKg, declaredValue);
        }

        @Override
        public double calculateShippingCharge() {
            return 80.00 + (15.00 * getWeightKg());
        }

        @Override
        public double calculateInsurance() {
            return 0.02 * getDeclaredValue();
        }
    }

    public static class FragileParcel extends Parcel implements Insurable {
        public FragileParcel(double weightKg, double declaredValue) {
            super("FRAGILE", weightKg, declaredValue);
        }

        @Override
        public double calculateShippingCharge() {
            double standardCharge = 40.00 + (10.00 * getWeightKg());
            return standardCharge + 50.00; // Handling fee
        }

        @Override
        public double calculateInsurance() {
            return 0.02 * getDeclaredValue();
        }
    }

    public static Parcel createParcel(String type, double weight, double declaredValue) {
        switch (type.trim().toUpperCase()) {
            case "STANDARD":
                return new StandardParcel(weight, declaredValue);
            case "EXPRESS":
                return new ExpressParcel(weight, declaredValue);
            case "FRAGILE":
            default:
                return new FragileParcel(weight, declaredValue);
        }
    }

    public static void processParcels(List<Parcel> parcels) {
        double grandTotal = 0.0;
        for (Parcel p : parcels) {
            double charge = p.calculateShippingCharge();
            double insurance = p.getInsuranceAmount();
            double total = p.calculateTotal();
            grandTotal += total;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n", p.getType(), charge, insurance, total);
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<Parcel> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String parcelLine = reader.readLine();
                        if (parcelLine == null) break;
                        String[] parts = parcelLine.trim().split("\\s+");
                        if (parts.length >= 3) {
                            list.add(createParcel(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
                        }
                    }
                    processParcels(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<Parcel> sample = new ArrayList<>();
        sample.add(new StandardParcel(3, 500));
        sample.add(new ExpressParcel(2, 1000));
        sample.add(new FragileParcel(4, 2000));
        processParcels(sample);
    }
}
