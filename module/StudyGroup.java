package pl.edu.pwr.uniapp.module;

import java.util.Arrays;

public class StudyGroup {

    private String code;
    private String name;
    private int lecturerId;
    private Student[] studentsList = new Student[15];
    private double[] studentMarks = new double[15];

    public StudyGroup() {
    }

    public StudyGroup(String code, String name, int lecturerId) {
        this.code = code;
        this.name = name;
        this.lecturerId = lecturerId;
    }

    @Override
    public String toString() {
        return String.format(
                "Kod: %s%n" +
                "Nazwa: %s%n" +
                "Prowadzący: %d%n" +
                "Uczestnicy:%n%s", code, name, lecturerId, attendantsList());
    }

    public Lecturer getGroupLecturerById(Lecturer[] lecturers) {
        for (Lecturer l : lecturers) {
            if (l.getId() == lecturerId) {
                return l;
            }
        }
        return null;
    }

    private String attendantsList() {
        String fullList = new String();
        for (Student s : studentsList) {
            if(s != null) {
                fullList += (s.toString() + "\n");
            }
        }
        return fullList;
    }

    public Student[] getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(Student[] studentsList) {
        this.studentsList = studentsList;
    }

    public void insertStudent(Student student) {
        for (int i = 0; i < studentsList.length; i++) {
            if (studentsList[i] == null) {
                studentsList[i] = student;
                return;
            }
        }
        System.out.println("Niestety, ta grupa zajęciowa jest już pełna!");
    }

    public void insertMark(Student student, double mark) {
        for(int i = 0; i < studentsList.length; i++) {
            if (!(studentsList[i] == null)) {
                if (studentsList[i].equals(student) && studentMarks[i] == 0.0) {
                    studentMarks[i] = mark;
                    return;
                }
            }
        }
        System.out.printf("Student o indeksie %d ma już ocenę z tego kursu!%n", student.getId());
    }

    public void getStudentMark(Student student) {
        for(int i = 0; i < studentsList.length; i++) {
            if (studentsList[i].equals(student)) {
                System.out.printf("%s: %.1f%n", name, studentMarks[i]);
                return;
            }
        }
    }

    public void getCourseMarks() {
        for (int i = 0; i < studentsList.length; i++) {
            if (studentsList[i] != null) {
                System.out.printf("%s: %.1f%n", studentsList[i].toString(), studentMarks[i]);
            }
        }
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }
}
