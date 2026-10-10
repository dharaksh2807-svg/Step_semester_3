package Week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 3: College Fee Counter
 * Models students and cleanly separates bus transportation users using an interface.
 */
public class CollegeFeeCounter {

    public interface BusTransportUser {
        double TRANSPORT_FEE = 12000.00;
    }

    public static abstract class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract double getTuition();

        public double getAdditionalFees() {
            double extra = 0.0;
            if (this instanceof BusTransportUser) {
                extra += BusTransportUser.TRANSPORT_FEE;
            }
            return extra;
        }

        public double calculateTotalFee() {
            return getTuition() + getAdditionalFees();
        }
    }

    public static class DayScholarStudent extends Student implements BusTransportUser {
        public DayScholarStudent(String name) {
            super(name);
        }

        @Override
        public double getTuition() {
            return 40000.00;
        }
    }

    public static class HostellerStudent extends Student {
        public static final double HOSTEL_FEE = 60000.00;

        public HostellerStudent(String name) {
            super(name);
        }

        @Override
        public double getTuition() {
            return 40000.00;
        }

        @Override
        public double getAdditionalFees() {
            return super.getAdditionalFees() + HOSTEL_FEE;
        }
    }

    public static class ScholarshipStudent extends Student implements BusTransportUser {
        public ScholarshipStudent(String name) {
            super(name);
        }

        @Override
        public double getTuition() {
            return 20000.00; // Half tuition
        }
    }

    public static Student createStudent(String type, String name) {
        switch (type.trim().toUpperCase()) {
            case "DAY_SCHOLAR":
                return new DayScholarStudent(name);
            case "HOSTELLER":
                return new HostellerStudent(name);
            case "SCHOLAR":
            default:
                return new ScholarshipStudent(name);
        }
    }

    public static void processFees(List<Student> students) {
        double totalCollected = 0.0;
        for (Student s : students) {
            double fee = s.calculateTotalFee();
            totalCollected += fee;
            System.out.printf("%s: %.2f%n", s.getName(), fee);
        }
        System.out.printf("Total Collected: %.2f%n", totalCollected);
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int n = Integer.parseInt(line.trim());
                    List<Student> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String studentLine = reader.readLine();
                        if (studentLine == null) break;
                        String[] parts = studentLine.trim().split("\\s+");
                        if (parts.length >= 2) {
                            list.add(createStudent(parts[0], parts[1]));
                        }
                    }
                    processFees(list);
                    return;
                }
            }
        } catch (Exception ignored) {
        }

        // Default sample execution
        List<Student> sample = new ArrayList<>();
        sample.add(new DayScholarStudent("Asha"));
        sample.add(new HostellerStudent("Ravi"));
        sample.add(new ScholarshipStudent("Neha"));
        processFees(sample);
    }
}
