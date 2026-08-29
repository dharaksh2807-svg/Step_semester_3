
/*P2. Login Attempt Simulator With Break
Scenario
A simple login screen for a coding-club internal tool gives a user up to 3 attempts to enter the correct access code
before locking them out.

Task
• Accept the correct access code and an array of up to 3 attempted codes.
• Use a for loop over the attempts, checking each one against the correct code.
• The moment a correct attempt is found, print "Access granted on attempt X" and use break to exit the loop
immediately.
• If the loop finishes without a match, print "Access denied — all attempts used".
Suggested Method Signature(s)
void simulateLogin(String correctCode, String[] attempts)
*/
package Week3;

import java.util.Scanner;

public class LoginAttempt {

    static void simulateLogin(String correctCode, String[] attempts) {

        boolean granted = false;

        for (int i = 0; i < attempts.length; i++) {

            if (attempts[i].equals(correctCode)) {
                System.out.println("Access granted on attempt " + (i + 1));
                granted = true;
                break;
            }
        }

        if (!granted) {
            System.out.println("Access denied — all attempts used");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter correct access code: ");
        String correctCode = sc.nextLine();

        String[] attempts = new String[3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter attempt " + (i + 1) + ": ");
            attempts[i] = sc.nextLine();
        }

        simulateLogin(correctCode, attempts);

        sc.close();
    }
}
