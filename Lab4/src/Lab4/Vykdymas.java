package Lab4;

import java.util.Locale;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class Vykdymas {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        FilmųTestai.atvaizdzioTestas();
        Langas.createAndShowGUI();
    }
}