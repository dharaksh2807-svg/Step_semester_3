package Week3;
/*P5. Day Name From Number
Scenario
A campus timetable app stores each weekday as a number (1 = Monday ... 7 = Sunday) internally, but needs to display
the actual day name to students.
Task
• Accept an integer from 1 to 7.
Page 2 of 8
CodInClub
Powered by BridgeLab
• Use a switch statement with a case for each number, printing the matching day name.
• Include a default case that prints "Invalid day number" for anything outside 1–7.
• Remember to include break after each case so execution doesn't fall through into the next one.
Suggested Method Signature(s)
void printDayName(int dayNumber)
*/
import java.util.Scanner;
public class timetable {

    static void printDayName(int dayNumber){
        switch(dayNumber){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day number");
        }
        
    }
    public static void main(String[]args){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter a number from 1 to 7: ");
        int dayNumber = sc.nextInt();
        printDayName(dayNumber);
        sc.close();
    }
}

