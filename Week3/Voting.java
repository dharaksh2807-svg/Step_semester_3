package Week3;
/*
P1. Voting Eligibility Checker
Scenario
SRM's student council election desk needs a one-line check at the registration table: is this student old enough to vote
in the campus election?
Task
• Accept a student's age as an integer.
• Build a boolean expression age >= 18.
• Using a single if / else statement, print "Eligible to vote" when the expression is true, otherwise print "Not
eligible to vote".
• No loop is needed for this one — just a boolean expression and if / else.
Suggested Method Signature(s)
void checkVotingEligibility(int age)
*/

import java.util.Scanner;
public class Voting{

    static void checkVotingEligiblity(int age){
        
        if(age >= 18){
            System.out.println("Eligible to vote");
        }else{
            System.out.println("Not eligible to vote");
        }
    }

        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter your age: ");
            int age = sc.nextInt();

            checkVotingEligiblity(age);

            sc.close();

        }
    }