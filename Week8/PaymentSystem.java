package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 1: Payment System Fee Calculation
 * Demonstrates polymorphism by processing various payment methods uniformly
 * without explicit type checking.
 */
public class PaymentSystem {

    public static abstract class PaymentMethod {
        private String type;
        private double amount;

        public PaymentMethod(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        public abstract double calculateAdjustedAmount();
    }

    public static class CardPayment extends PaymentMethod {
        public CardPayment(double amount) {
            super("CARD", amount);
        }

        @Override
        public double calculateAdjustedAmount() {
            return getAmount() * 1.02; // 2% processing fee
        }
    }

    public static class WalletPayment extends PaymentMethod {
        public WalletPayment(double amount) {
            super("WALLET", amount);
        }

        @Override
        public double calculateAdjustedAmount() {
            return getAmount() * 1.01; // 1% processing fee
        }
    }

    public static class BankTransferPayment extends PaymentMethod {
        public BankTransferPayment(double amount) {
            super("BANKTRANSFER", amount);
        }

        @Override
        public double calculateAdjustedAmount() {
            return getAmount(); // 0% processing fee
        }
    }

    public static PaymentMethod createPayment(String type, double amount) {
        switch (type.trim().toUpperCase()) {
            case "CARD":
                return new CardPayment(amount);
            case "WALLET":
                return new WalletPayment(amount);
            case "BANKTRANSFER":
            default:
                return new BankTransferPayment(amount);
        }
    }

    public static void processPayments(List<PaymentMethod> payments) {
        double total = 0.0;
        for (PaymentMethod p : payments) {
            double adjusted = p.calculateAdjustedAmount();
            total += adjusted;
            System.out.printf("%s: %.2f%n", p.getType(), adjusted);
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
                    List<PaymentMethod> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String itemLine = reader.readLine();
                        if (itemLine == null) break;
                        String[] parts = itemLine.trim().split("\\s+");
                        if (parts.length >= 2) {
                            list.add(createPayment(parts[0], Double.parseDouble(parts[1])));
                        }
                    }
                    processPayments(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<PaymentMethod> sample = new ArrayList<>();
        sample.add(new CardPayment(1000));
        sample.add(new WalletPayment(500));
        sample.add(new BankTransferPayment(2000));
        processPayments(sample);
    }
}
