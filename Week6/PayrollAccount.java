package Week6;

/**
 * M2. Payroll Salary Management
 * Encapsulates payroll data ensuring valid bonuses, taxes, and read-only net salary access.
 */
public class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Opening basic salary cannot be negative. Initialized to 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Bonus amount must be greater than 0.");
            return;
        }
        this.bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Error: Tax percentage must be between 0 and 100.");
            return;
        }
        double taxAmount = this.basicSalary * (percent / 100.0);
        this.basicSalary -= taxAmount;
        if (percent == (long) percent) {
            System.out.println("Tax deducted: " + (long) percent + "%");
        } else {
            System.out.println("Tax deducted: " + percent + "%");
        }
    }

    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}
