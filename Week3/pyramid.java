/*
P1. Number Pyramid Pattern Printer
Scenario
A classroom whiteboard exercise on nested loops asks students to reproduce a simple numeric pyramid, one row
taller than the last, to build comfort with loops inside loops.
Task
• Accept the number of rows N.
• Use an outer for loop for each row from 1 to N.
CodInClub
Powered by BridgeLab
• Use an inner for loop that prints the row number that many times on the same line.
• Move to a new line after each row completes.
Suggested Method Signature(s)
void printNumberPyramid(int n)
Sample Input / Output
Input Output
n = 4 1 2 2 3 3 3 4 4 4 4
Concepts covered: Nested for loops, outer/inner loop counters, pattern printing.
 */
package Week3;
import java.util.Scanner;
public class pyramid {
    
    static void printNumberPyramid(int n){
        for(int i=1; i<=n; i++){
            for(int j=1; j<=i; j++){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int n = sc.nextInt();
        printNumberPyramid(n);
        sc.close();
    }
}