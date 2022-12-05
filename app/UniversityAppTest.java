package pl.edu.pwr.uniapp.app;

import pl.edu.pwr.uniapp.module.Lecturer;
import pl.edu.pwr.uniapp.module.Student;
import pl.edu.pwr.uniapp.module.StudyGroup;
import pl.edu.pwr.uniapp.module.UniversityApp;
import pl.edu.pwr.uniapp.utils.UniversityAppUtils;

import java.util.Random;

public class UniversityAppTest {
    public static void main(String[] args) {
        Random r = new Random();
        UniversityApp uniApp = new UniversityApp();

        testCreateLecturer(r, uniApp);
        testCreateGroup(r, uniApp);
        testAddStudentToGroup(r, uniApp);
        testPrintGroupInfo(uniApp);
        testAddGrade(r, uniApp);

        uniApp.printGradesForStudent(267811);
        System.out.println();

        testPrintGradesForGroup(uniApp);

        uniApp.printAllStudents();


    }

    /**
     * Klasa testująca metodę UniversityApp.tprintGradesForGroup()
     *
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testPrintGradesForGroup(UniversityApp uniApp) {
        for (StudyGroup sg : uniApp.getStudyGroups()) {
            System.out.println(sg.getCode());
            uniApp.printGradesForGroup(sg.getCode());
            System.out.println();
        }
    }

    /**
     * Klasa testująca metodę UniversityApp.printGroupInfo()
     *
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testPrintGroupInfo(UniversityApp uniApp) {
        for (StudyGroup sg : uniApp.getStudyGroups()) {
            uniApp.printGroupInfo(sg.getCode());
            System.out.println();
        }
        uniApp.printGroupInfo("fiz-kra-12");
        System.out.println();
    }

    /**
     * Klasa testująca metodę UniversityApp.addGrade()
     *
     * @param r      obiekt klasy Random, służący do losowego generowania pól testowanych obiektów
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testAddGrade(Random r, UniversityApp uniApp) {
        for (StudyGroup sg : uniApp.getStudyGroups()) {
            for (Student s : sg.getStudentsList()) {
                if (s != null) {
                    uniApp.addGrade(
                            s.getId(),
                            sg.getCode(),
                            UniversityAppUtils.GRADES[r.nextInt(UniversityAppUtils.GRADES.length)]);
                }
            }
        }
        System.out.println();
    }

    /**
     * Klasa testująca metodę UniversityApp.addStudentToGroup()
     *
     * @param r      obiekt klasy Random, służący do losowego generowania pól testowanych obiektów
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testAddStudentToGroup(Random r, UniversityApp uniApp) {
        System.out.println("Testing addStudentToGroup()");
        int counter = 0;
        for (StudyGroup sg : uniApp.getStudyGroups()) {
            counter++;
            int groupSize = r.nextInt(5, 16);
            for (int j = 0; j < groupSize; j++) {
                if ((double) r.nextInt() / Integer.MAX_VALUE > 0.5) {
                    uniApp.addStudentToGroup(
                            261000 + j + 1000 * counter,
                            sg.getCode(),
                            UniversityAppUtils.NAMES_FEMALE[r.nextInt(0, 8)],
                            UniversityAppUtils.SURNAMES_FEMALE[r.nextInt(0, 8)]);
                } else {
                    uniApp.addStudentToGroup(
                            261000 + j + 1000 * counter,
                            sg.getCode(),
                            UniversityAppUtils.NAMES_MALE[r.nextInt(0, 8)],
                            UniversityAppUtils.SURNAMES_MALE[r.nextInt(0, 8)]);
                }
            }
        }
        System.out.println();
    }

    /**
     * Klasa testująca metodę UniversityApp.createGroup()
     *
     * @param r      obiekt klasy Random, służący do losowego generowania pól testowanych obiektów
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testCreateGroup(Random r, UniversityApp uniApp) {
        System.out.println("Testing: createGroup()");
        for (String name : UniversityAppUtils.COURSE_NAMES) {
            Lecturer l = uniApp.getLecturersList()[r.nextInt(uniApp.getLecturersList().length)];
            uniApp.createGroup(
                    UniversityAppUtils.generateGroupCode(name, l.getLastName()),
                    name,
                    l.getId());
        }
        for (StudyGroup sg : uniApp.getStudyGroups()) {
            System.out.println(sg.toString());
        }
        System.out.println();
    }

    /**
     * Klasa testująca metodę UniversityApp.createLecturer()
     *
     * @param r      obiekt klasy Random, służący do losowego generowania pól testowanych obiektów
     * @param uniApp obiekt klasy UniversityApp związany z wykorzystywanymi metodami i przechowywanymi danymi
     */
    private static void testCreateLecturer(Random r, UniversityApp uniApp) {
        System.out.println("Testing: createLecturer()");
        for (int i = 0; i < 5; i++) {
            if ((double) r.nextInt() / Integer.MAX_VALUE > 0.5) {
                uniApp.createLecturer(
                        r.nextInt(1000, 10000), // id
                        UniversityAppUtils.TITLES[r.nextInt(UniversityAppUtils.TITLES.length)], // tytul
                        UniversityAppUtils.NAMES_FEMALE[r.nextInt(UniversityAppUtils.NAMES_FEMALE.length)], // imię
                        UniversityAppUtils.SURNAMES_FEMALE[r.nextInt(UniversityAppUtils.SURNAMES_FEMALE.length)]); // nazwisko
            } else {
                uniApp.createLecturer(
                        r.nextInt(1000, 10000), // id
                        UniversityAppUtils.TITLES[r.nextInt(UniversityAppUtils.TITLES.length)], // tytul
                        UniversityAppUtils.NAMES_MALE[r.nextInt(UniversityAppUtils.NAMES_MALE.length)], // imię
                        UniversityAppUtils.SURNAMES_MALE[r.nextInt(UniversityAppUtils.SURNAMES_MALE.length)]); // nazwisko
            }
        }
        for (Lecturer l : uniApp.getLecturersList()) {
            System.out.println(l.toString());
        }
        System.out.println();
    }
}
