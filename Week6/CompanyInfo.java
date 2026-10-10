package Week6;

/**
 * M5. Employee and Company Information Management
 * Demonstrates static fields and static methods shared across all instances of a class.
 */
public class CompanyInfo {

    public static class Employee {
        String empName;
        double salary;
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        public Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        public static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", 50000);
        Employee e2 = new Employee("Bob", 60000);
        Employee e3 = new Employee("Charlie", 55000);

        // Called through the class name, not through any object
        Employee.printCompanyInfo();
    }
}
