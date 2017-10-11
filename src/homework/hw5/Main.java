package homework.hw5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Josiah on 10/10/17.
 */
public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader(new File("/Users/Josiah/git/COMP-121/resources/homework/hw5/grades.txt"));
            Scanner scanner = new Scanner(fileReader);

            List<String> grades = new ArrayList<>();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] gradeArray = line.split(" ");
                for (String grade : gradeArray) {
                    String[] gradePieces = grade.split("=");
                    grades.add(String.format("Grade %s is %s", gradePieces[0], gradePieces[1]));
                }
            }

            for (String grade : grades) {
                System.out.println(grade);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
