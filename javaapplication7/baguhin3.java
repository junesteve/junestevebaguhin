

package javaapplication7;

import java.util.Scanner;

public class NumberChecker {

    public static void checkNumber(int number, int instance) {
        System.out.println("//instance " + instance);
        if (number > 0) {
            System.out.println(number + " is a positive number\n");
        } else if (number < 0) {
            System.out.println(number + " is a negative number\n");
        } else {
            System.out.println("The number is zero\n");
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("Enter a number: ");
                int number = input.nextInt();
                checkNumber(number, i);
            }
        }
    }
}
