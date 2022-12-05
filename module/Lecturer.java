package pl.edu.pwr.uniapp.module;

/**
 * Klasa zawierająca informacje o prowadzącym zajęcia
 */
public class Lecturer {
    private int id;
    private String degree;
    private String firstName;
    private String lastName;

    public Lecturer() {
    }

    public Lecturer(int id, String degree, String firstName, String lastName) {
        this.id = id;
        this.degree = degree;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Prowadzący %d: %s %s %s", id, degree, firstName, lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
