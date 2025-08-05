package baguhin3;

import java.util.Scanner;

public class NumberChecker {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int number;
            
            
            System.out.println("//instance 1");
            System.out.print("Enter a number: ");
            number = input.nextInt();
            
            if (number > 0) {
                System.out.println(number + " is a positive number\n");
            } else if (number < 0) {
                System.out.println(number + " is a negative number\n");
            } else {
                System.out.println("Inputted number Zero\n");
            }
            
            
            System.out.println("//instance 2");
            System.out.print("Enter a number: ");
            number = input.nextInt();
            
            if (number > 0) {
                System.out.println(number + " is a positive number\n");
            } else if (number < 0) {
                System.out.println(number + " is a negative number\n");
            } else {
                System.out.println("Inputted number Zero\n");
            }
            
            
            System.out.println("//instance 3");
            System.out.print("Enter a number: ");
            number = input.nextInt();
            
            if (number > 0) {
                System.out.println(number + " is a positive number");
            } else if (number < 0) {
                System.out.println(number + " is a negative number");
            } else {
                System.out.println("Inputted number Zero");
            }
        }
    }
}