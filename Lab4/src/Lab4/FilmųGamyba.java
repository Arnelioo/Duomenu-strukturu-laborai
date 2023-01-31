package Lab4;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import laborai.gui.MyException;

public class FilmųGamyba {
    private static final String ID_CODE = "TA";      //  ***** nauja
    private static int serNr = 10000;               //  ***** nauja

    private Filmas[] filmai;
    private String[] raktai;
    private int kiekis = 0, idKiekis = 0;

    public static Filmas[] gamintiFilmus(int kiekis) {
        Filmas[] filmai = IntStream.range(0, kiekis)
                .mapToObj(i -> new Filmas.Builder().buildRandom())
                .toArray(Filmas[]::new);
        Collections.shuffle(Arrays.asList(filmai));
        return filmai;
    }

    public static String[] gamintiFilmuIds(int kiekis) {
        String[] raktai = IntStream.range(0, kiekis)
                .mapToObj(i -> ID_CODE + (serNr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(raktai));
        return raktai;
    }

    public Filmas[] gamintiIrParduotiFilmus(int aibesDydis,
            int aibesImtis) throws MyException {
        if (aibesImtis > aibesDydis) {
            aibesImtis = aibesDydis;
        }
        filmai = gamintiFilmus(aibesDydis);
        raktai = gamintiFilmuIds(aibesDydis);
        this.kiekis = aibesImtis;
        return Arrays.copyOf(filmai, aibesImtis);
    }

    // Imamas po vienas elementas iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojama
    // nuosava situacija ir išmetamas pranešimas.
    public Filmas parduotiFilma() {
        if (filmai == null) {
            throw new MyException("carsNotGenerated");
        }
        if (kiekis < filmai.length) {
            return filmai[kiekis++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }

    public String gautiIsBazesFilmoId() {
        if (raktai == null) {
            throw new MyException("carsIdsNotGenerated");
        }
        if (idKiekis >= raktai.length) {
            idKiekis = 0;
        }
        return raktai[idKiekis++];
    }
}
