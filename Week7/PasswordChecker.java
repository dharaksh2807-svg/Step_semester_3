package Week7;

/**
 * Problem 3: The Password Checker
 * Encapsulates sensitive credentials, evaluating password strength without revealing the raw string.
 */
public class PasswordChecker {
    private final String password;

    public PasswordChecker(String password) {
        this.password = password != null ? password : "";
    }

    public String getStrength() {
        int length = this.password.length();
        if (length < 6) {
            return "Weak";
        } else if (length <= 9) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        PasswordChecker pc = new PasswordChecker("abcd");
        System.out.println("pc.getStrength() -> \"" + pc.getStrength() + "\"");

        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("pc2.getStrength() -> \"" + pc2.getStrength() + "\"");
    }
}
