
import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String name;
            int sci, history, math, mapeh, arbc;
            int total;
            double average;
            String remark;
            System.out.print("Enter name: ");
            name = input.nextLine();
            System.out.print("Enter marks in Science: ");
            sci = input.nextInt();
            System.out.print("Enter marks in History: ");
            history = input.nextInt();
            System.out.print("Enter marks in Math: ");
            math = input.nextInt();
            System.out.print("Enter marks in MAPEH: ");
            mapeh = input.nextInt();
            System.out.print("Enter marks in Arabic: ");
            arbc = input.nextInt();
            total = sci + history + math + mapeh + arbc;
            average = total / 5.0;
            // Determine remark
            if (average >= 90 && average <= 100) {
                remark = "Excellent";
            } else if (average >= 85) {
                remark = "Very Good";
            } else if (average >= 80) {
                remark = "Good";
            } else if (average >= 75) {
                remark = "Fair";
            } else {
                remark = "Poor";
            }   System.out.println("\nTotal Marks: " + total);
            System.out.printf("Your average is: %.2f\n", average);
            System.out.println("Remarks: " + remark);
            if (sci < 75 || history < 75 || math < 75 || mapeh < 75 || arbc < 75) {
                System.out.println("If subject marks is less than 75, Student fails in that subject.");
            } else {
                System.out.println("Congrats! Mike you Passed.");
            }
        }
    }
}
