import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> students = new ArrayList<>();
        ArrayList<Double> grades = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input student data
        for (int i = 0; i < n; i++) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter grade for " + name + ": ");
            double grade = sc.nextDouble();
            sc.nextLine();

            students.add(name);
            grades.add(grade);
        }

        // Calculate total
        double total = 0;

        for (double grade : grades) {
            total += grade;
        }

        // Calculate average
        double average = total / grades.size();

        // Find highest and lowest
        double highest = grades.get(0);
        double lowest = grades.get(0);

        for (double grade : grades) {

            if (grade > highest) {
                highest = grade;
            }

            if (grade < lowest) {
                lowest = grade;
            }
        }

        // Display report
        System.out.println("\n========== STUDENT GRADE REPORT ==========");

        for (int i = 0; i < students.size(); i++) {
            System.out.println(
                students.get(i) + " : " + grades.get(i)
            );
        }

        System.out.println("-------------------------------------------");
        System.out.println("Average Score : " + average);
        System.out.println("Highest Score : " + highest);
        System.out.println("Lowest Score  : " + lowest);

        sc.close();
    }
}