package pl.edu.pwr.uniapp.module;

import pl.edu.pwr.uniapp.utils.LecturerUtils;
import pl.edu.pwr.uniapp.utils.StudentUtils;
import pl.edu.pwr.uniapp.utils.StudyGroupUtils;

public class UniversityApp {
    private Lecturer[] lecturersList = new Lecturer[0];
    private StudyGroup[] studyGroups = new StudyGroup[0];

    public UniversityApp() {
    }

    public Lecturer[] getLecturersList() {
        return lecturersList;
    }

    private void setLecturersList(Lecturer[] lecturersList) {
        this.lecturersList = lecturersList;
    }

    public StudyGroup[] getStudyGroups() {
        return studyGroups;
    }

    private void setStudyGroups(StudyGroup[] studyGroups) {
        this.studyGroups = studyGroups;
    }

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */
    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (LecturerUtils.idAvailable(id, lecturersList)) {
            int lLen = lecturersList == null ? 1 : lecturersList.length + 1;
            Lecturer[] temp = lecturersList;
            lecturersList = new Lecturer[lLen];
            System.arraycopy(temp, 0, lecturersList, 0, temp.length);
            lecturersList[temp.length] = new Lecturer(id, degree, firstName, lastName);
        } else {
            System.out.printf("Prowadzący z id %d już istnieje!", id);
        }
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
    public void createGroup(String code, String name, int lecturerId) {
        if (!StudyGroupUtils.groupCodeAvailable(code, studyGroups)) {
            System.out.printf("Grupa o kodzie %s już istnieje!", code);
        } else if (!LecturerUtils.idValid(lecturerId, lecturersList)) {
            System.out.printf("Prowadzący z id %d nie istnieje!", lecturerId);
        } else {
            int sGLen = studyGroups == null ? 1 : studyGroups.length + 1;
            StudyGroup[] temp = studyGroups;
            studyGroups = new StudyGroup[sGLen];
            System.arraycopy(temp, 0, studyGroups, 0, temp.length);
            studyGroups[temp.length] = new StudyGroup(code, name, lecturerId);
        }
    }


    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        if (!StudyGroupUtils.groupCodeValid(groupCode, studyGroups)) {
            System.out.printf("Grupa o kodzie %s nie istnieje!%n", groupCode);
        } else {
            StudyGroup sg = StudyGroupUtils.getStudyGroupByCode(groupCode, studyGroups);
            sg.insertStudent(new Student(firstName, lastName, index));
        }
    }


    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */
    public void printGroupInfo(String groupCode) {
        StudyGroup sg = StudyGroupUtils.getStudyGroupByCode(groupCode, studyGroups);
        if (sg != null) {
            System.out.println(sg);
        } else {
            System.out.printf("Grupa o kodzie %s nie istnieje!%n", groupCode);
        }
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */
    public void addGrade(int studentIndex, String groupCode, double grade) {
        StudyGroup sg = StudyGroupUtils.getStudyGroupByCode(groupCode, studyGroups);
        if (sg != null) {
            Student s = StudyGroupUtils.getGroupStudentById(studentIndex, sg);
            if (s != null) {
                sg.insertMark(s, grade);
            } else {
                System.out.printf("Student o indeksie %d nie jest zapisany do grupy %s!%n", studentIndex, groupCode);
            }
        } else {
            System.out.printf("Grupa o kodzie %s nie istnieje%n", groupCode);
        }
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indeksu studenta, dla którego wyświetlić oceny
     */
    public void printGradesForStudent(int index) {
        for (StudyGroup sg : studyGroups) {
            Student s = StudyGroupUtils.getGroupStudentById(index, sg);
            if (s != null) {
                sg.getStudentMark(s);
            }
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */
    public void printGradesForGroup(String groupCode) {
        StudyGroup sg = StudyGroupUtils.getStudyGroupByCode(groupCode, studyGroups);
        if (sg != null) {
            sg.getCourseMarks();
        } else {
            System.out.printf("Grupa o kodzie %s nie istnieje!%n", groupCode);
        }
    }

    /**
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */
    public void printAllStudents() {
        Student[] allStudents = new Student[1];
        for (StudyGroup sg : studyGroups) {
            Student[] students = sg.getStudentsList();
            for (Student s : students) {
                if (!StudentUtils.alreadyExists(s, allStudents)) {
                    Student[] temp = allStudents;
                    allStudents = new Student[temp.length + 1];
                    System.arraycopy(temp, 0, allStudents, 0, temp.length);
                    allStudents[temp.length] = s;
                }
            }
        }
        for (Student s : allStudents) {
            if (s != null) {
                System.out.println(s);
            }
        }
    }
}
