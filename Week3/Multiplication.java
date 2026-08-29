/*
P4. Multiplication Table Generator With Input Validation
Scenario
A revision worksheet generator should refuse to print a multiplication table for an invalid number (0 or negative),
reprompting conceptually before generating the table for the first valid number in a batch.
Task
• Accept an array of candidate numbers to try, in order.
• Use a for loop over the candidates; for each one, check with an if statement whether it is 1 or greater.
• If a candidate is invalid (less than 1), print "Skipping invalid number: ..." and use continue to move to the next
candidate.
• The moment a valid candidate is found, print its multiplication table from 1 to 10 using a nested for loop, then
use break to stop processing further candidates.
Suggested Method Signature(s)
void generateFirstValidTable(int[] candidates)
Sample Input / Output
Input Output
candidates = {-3, 0, 7, 9} Skipping invalid number: -3 Skipping invalid number: 0 7 x 1 =
7 7 x 2 = 14 ... 7 x 10 = 70
Concepts covered: for loops, continue, break, if validation, nested loops.
 */

package Week3;
import java.util.Scanner;

public class Multiplication {
    static void generateFirstValidTable(int[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] < 1) {
                System.out.println("Skipping invalid number: " + candidates[i]);
                continue;
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println(candidates[i] + " x " + j + " = " + (candidates[i] * j));
            }
            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of candidates: ");
        int n = sc.nextInt();
        int[] candidates = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter candidate " + (i + 1) + ": ");
            candidates[i] = sc.nextInt();
        }

        generateFirstValidTable(candidates);
        sc.close();
    }
}
