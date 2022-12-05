package pl.edu.pwr.uniapp.utils;

import pl.edu.pwr.uniapp.module.Student;
import pl.edu.pwr.uniapp.module.StudyGroup;

/**
 * Klasa przechowująca metody oraz zmienne statyczne przydatne przy interakacjach z klasą StudyGroup
 */
public class StudyGroupUtils {
    /**
     * Metoda sprawdzająca czy kod grupy jest dostępny do wykorzystania
     * @param code      - kod grupy do sprawdzania
     * @param sgList    - lista istniejących grup
     * @return          - true jeśli kod grupy jest dostępny, false w przeciwnym wypadku
     */
    public static boolean groupCodeAvailable(String code, StudyGroup[] sgList) {
        if (sgList != null) {
            for (StudyGroup s : sgList) {
                if (s.getCode().equals(code)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Metoda sprawdzająca czy kod grupy istnieje
     * @param code      - kod grupy do sprawdzenia
     * @param sgList    - lista istniejących grup
     * @return          - trure jeśli kod grupy istnieje, false w przeciwnym wypadku
     */
    public static boolean groupCodeValid(String code, StudyGroup[] sgList) {
        if (sgList != null) {
            for (StudyGroup s : sgList) {
                if (s.getCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metoda zwracająca obiekt StudyGroup o zadanym kodzie
     * @param code      - kod grupy
     * @param sgList    - lista grup
     * @return          - obiekt grupy
     */
    public static StudyGroup getStudyGroupByCode(String code, StudyGroup[] sgList) {
        if (sgList != null) {
            for (StudyGroup s : sgList) {
                if (s.getCode().equals(code)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Metoda zwracająca obiekt Student na podstawie numeru indeksu
     * @param id - indeks studenta
     * @param sg - grupa zajęciowa
     * @return   - obiekt Student o zadanym indeksie
     */
    public static Student getGroupStudentById(int id, StudyGroup sg) {
        for (Student s : sg.getStudentsList()) {
            if(s != null) {
                if (s.getId() == id) {
                    return s;
                }
            }
        }
        return null;
    }
}
