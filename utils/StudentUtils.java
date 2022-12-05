package pl.edu.pwr.uniapp.utils;

import pl.edu.pwr.uniapp.module.Student;

/**
 * Klasa przechowująca metody oraz zmienne statyczne przydatne przy interakacjach z klasą Student
 */
public class StudentUtils {
    public static boolean alreadyExists(Student student, Student[] students) {
        for (Student s : students) {
            if (student != null) {
                if (student.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
