package Lab3;

import java.util.Locale;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class LangoPaleidimas {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        FilmoTestai.aibėsTestas();
        Langas.createAndShowGUI();
    }
}
