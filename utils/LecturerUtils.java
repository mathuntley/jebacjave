package pl.edu.pwr.uniapp.utils;

import pl.edu.pwr.uniapp.module.Lecturer;

/**
 * Klasa przechowująca metody oraz zmienne statyczne przydatne przy interakacjach z klasą Lecturer
 */
public class LecturerUtils {
    /**
     * Metoda sprawdzająca czy prowadzący o danym id już istnieje
     *
     * @param id    - id prowadzącego
     * @param lList - tablica prowadzących
     * @return - true jeśli zadany indeks prowadzącego nie istnieje, false w innym przypadku
     */
    public static boolean idAvailable(int id, Lecturer[] lList) {
        if (lList != null) {
            for (Lecturer l : lList) {
                if (l.getId() == id) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Metoda sprawdzająca czy dane id prowadzącego istnieje
     *
     * @param id    - id prowadzącego
     * @param lList - tablica prowadzących
     * @return - true jeśli zadany indeks prowadzącego istnieje, false w innym przypadku
     */
    public static boolean idValid(int id, Lecturer[] lList) {
        if (lList != null) {
            for (Lecturer l : lList) {
                if (l.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
