package pl.edu.pwr.uniapp.utils;

import pl.edu.pwr.uniapp.module.Student;

import java.util.Random;

/**
 * Klasa przechowująca metody oraz zmienne statyczne przydatne przy interakacjach z klasą UniversityApp
 */
public class UniversityAppUtils {

    public static final String[] NAMES_FEMALE = {
            "Zofia", "Julia", "Anna", "Diana", "Danuta", "Maja", "Sara", "Paulina"
    };
    public static final String[] NAMES_MALE = {
            "Adam", "Tadeusz", "Stanisław", "Marek", "Sebastian", "Szymon", "Mateusz", "Zbigniew"
    };
    public static final String[] SURNAMES_MALE = {
            "Nowicki", "Ulam", "Banach", "Trocki", "Kraszewski", "Rozmarynowski", "Wrona", "Kruk"
    };
    public static final String[] SURNAMES_FEMALE = {
            "Kowalska", "Brodka", "Curie", "Malinowska", "Słowacka", "Murawska", "Wróbel", "Mewa"
    };
    public static final String[] COURSE_NAMES = {
      "Podstawy programowania", "Fizyka 2.7", "Biomechanika", "Architektura", "Bazy danych"
    };
    public static final String[] TITLES = {
            "dr hab. inż.", "mgr inż.", "prof. dr hab. inż.", "dr"
    };
    public static final double[] GRADES = {
            2.0, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5
    };

    public static Student[] generateStudents() {
        Random r = new Random();
        Student[] students = new Student[50];
        for (int i = 0; i < students.length; i++) {
            if ((double)r.nextInt() / Integer.MAX_VALUE > 0.5) {
                students[i] = new Student(NAMES_FEMALE[r.nextInt(0, 8)], SURNAMES_FEMALE[r.nextInt(0, 8)]);
            } else {
                students[i] = new Student(NAMES_MALE[r.nextInt(0, 8)], SURNAMES_MALE[r.nextInt(0, 8)]);
            }
        }
        return students;
    }

    /**
     * Metoda generująca kody grup zajęciowych
     * @param name          - nazwa kursu
     * @param lectLastName  - nazwisko prowadzącego
     * @return              kod grupy w formie {4 pierwsze litery nazwy kursu}-{4 pierwsze litery nazwiska wykładowcy}-{losowa liczba z zakresu 10 - 99}
     */
    public static String generateGroupCode(String name, String lectLastName) {
        Random r = new Random();
        return name.toLowerCase().replaceAll(" ", "").substring(0, 4) + "-" + lectLastName.toLowerCase().substring(0, 4) + "-" + r.nextInt(10, 99);
    }
}
