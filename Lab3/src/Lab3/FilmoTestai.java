package Lab3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import laborai.studijosktu.*;


/*
 * Aibės testavimas be Swing'o
 *
 */
public class FilmoTestai {

    static Filmas[] filmuBaze;
    static SortedSetADTx<Filmas> aSerija = new BstSetKTUx(new Filmas(), Filmas.pagalPelną);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        aibėsTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis, int generN) {
        filmuBaze = new Filmas[generN];
        for (int i = 0; i < generN; i++) {
            filmuBaze[i] = new Filmas.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(filmuBaze));
        aSerija.clear();
        for (int i = 0; i < kiekis; i++) {
            aSerija.add(filmuBaze[i]);
        }
        return aSerija;
    }

    public static void aibėsTestas() throws CloneNotSupportedException {
        Filmas a1 = new Filmas("Filmas1", "Drama", "Studija1", "Režisierius1", 2000, 460.3);
        Filmas a2 = new Filmas.Builder()
                .filmoPav("Filmas2")
                .žanras("Dokumentinis")
                .studija("Studija2")
                .režisierius("Režisierius2")
                .išleidimoMetai(2013)
                .pelnas(723.0)
                .build();
        Filmas a3 = new Filmas.Builder().buildRandom();
        Filmas a4 = new Filmas("Filmas4 Veiksmo Studija3 Režisierius4 1997 635.0");
        Filmas a5 = new Filmas("Filmas5 Nuotykių Studija4 Režisierius5 2012 1003.0");
        Filmas a6 = new Filmas("Filmas7 Siaubo Studija2 Režisierius6 2016 368.0");
        Filmas a7 = new Filmas("Filmas10 Veiksmo Studija3 Režisierius4 2003 865.0");
        Filmas a8 = new Filmas("Filmas5 Drama Studija4 Režisierius5 2002 999.0");
        Filmas a9 = new Filmas("Filmas8 Siaubo Studija4 Režisierius6 2019 653.0");

        Filmas[] filmųMasyvas = {a9, a7, a8, a5, a1, a6};

        Ks.oun("Filmų Aibė:");
        SortedSetADTx<Filmas> filmuAibe = new BstSetKTUx(new Filmas());

        for (Filmas a : filmųMasyvas) {
            filmuAibe.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + filmuAibe.size());
        }
        Ks.oun("");
        Ks.oun(filmuAibe.toVisualizedString(""));

        SortedSetADTx<Filmas> filmuAibeKopija
                = (SortedSetADTx<Filmas>) filmuAibe.clone();

        filmuAibeKopija.add(a2);
        filmuAibeKopija.add(a3);
        filmuAibeKopija.add(a4);
        Ks.oun("Papildyta filmuAibės kopija:");
        Ks.oun(filmuAibeKopija.toVisualizedString(""));

        Ks.oun("Originalas:");
        Ks.ounn(filmuAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Filmas a : filmųMasyvas) {
            Ks.oun(a + ": " + filmuAibe.contains(a));
        }
        Ks.oun(a2 + ": " + filmuAibe.contains(a2));
        Ks.oun(a3 + ": " + filmuAibe.contains(a3));
        Ks.oun(a4 + ": " + filmuAibe.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Filmas a : filmųMasyvas) {
            Ks.oun(a + ": " + filmuAibeKopija.contains(a));
        }
        Ks.oun(a2 + ": " + filmuAibeKopija.contains(a2));
        Ks.oun(a3 + ": " + filmuAibeKopija.contains(a3));
        Ks.oun(a4 + ": " + filmuAibeKopija.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + filmuAibeKopija.size());
        for (Filmas a : new Filmas[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
            filmuAibeKopija.remove(a);
            Ks.oun("Iš filmųAibės kopijos pašalinama: " + a + ". Jos dydis: " + filmuAibeKopija.size());
        }
        Ks.oun("");

        Ks.oun("Filmų aibė su iteratoriumi:");
        Ks.oun("");
        for (Filmas a : filmuAibe) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Filmų aibė AVL-medyje:");
        SortedSetADTx<Filmas> filmuAibe3 = new AvlSetKTUx(new Filmas());
        for (Filmas a : filmųMasyvas) {
            filmuAibe3.add(a);
        }
        Ks.ounn(filmuAibe3.toVisualizedString(""));

        Ks.oun("Filmų aibė su iteratoriumi:");
        Ks.oun("");
        for (Filmas a : filmuAibe3) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Filmų aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = filmuAibe3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Filmų aibės toString() metodas:");
        Ks.ounn(filmuAibe3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        filmuAibe.clear();
        filmuAibe3.clear();

        Ks.oun("");
        Ks.oun("Filmų aibė DP-medyje:");
        filmuAibe.load("Duomenys\\FilmuDuomenys.txt");
        Ks.ounn(filmuAibe.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Filmų aibė AVL-medyje:");
        filmuAibe3.load("Duomenys\\FilmuDuomenys.txt");
        Ks.ounn(filmuAibe3.toVisualizedString(""));

        SetADT<String> filmuAibe4 = FilmoApskaita.filmųŽanrai(filmųMasyvas);
        Ks.oun("Pasikartojantys filmų žanrai:\n" + filmuAibe4.toString());
    }
}
