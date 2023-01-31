package Lab3;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import laborai.gui.MyException;


public class FilmųGamyba {

    private static Filmas[] filmai;
    private static int pradinisIndeksas = 0, galinisIndeksas = 0;
    private static boolean arPradzia = true;

    public static Filmas[] generuoti(int kiekis) {
        filmai = new Filmas[kiekis];
        for (int i = 0; i < kiekis; i++) {
            filmai[i] = new Filmas.Builder().buildRandom();
        }
        return filmai;
    }

    public static Filmas[] generuotiIrIsmaisyti(int aibesDydis,
            double isbarstymoKoeficientas) throws MyException {
        return generuotiIrIsmaisyti(aibesDydis, aibesDydis, isbarstymoKoeficientas);
    }

    /**
     *
     * @param aibesDydis
     * @param aibesImtis
     * @param isbarstymoKoeficientas
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws MyException
     */
    public static Filmas[] generuotiIrIsmaisyti(int aibesDydis,
            int aibesImtis, double isbarstymoKoeficientas) throws MyException {
        filmai = generuoti(aibesDydis);
        return ismaisyti(filmai, aibesImtis, isbarstymoKoeficientas);
    }

    // Galima paduoti masyvą išmaišymui iš išorės
    public static Filmas[] ismaisyti(Filmas[] autoBaze,
            int kiekis, double isbarstKoef) throws MyException {
        if (autoBaze == null) {
            throw new IllegalArgumentException("FilmųBaze yra null");
        }
        if (kiekis <= 0) {
            throw new MyException(String.valueOf(kiekis), 1);
        }
        if (autoBaze.length < kiekis) {
            throw new MyException(autoBaze.length + " >= " + kiekis, 2);
        }
        if ((isbarstKoef < 0) || (isbarstKoef > 1)) {
            throw new MyException(String.valueOf(isbarstKoef), 3);
        }

        int likusiuKiekis = autoBaze.length - kiekis;
        int pradziosIndeksas = (int) (likusiuKiekis * isbarstKoef / 2);

        Filmas[] pradineFilmuImtis = Arrays.copyOfRange(autoBaze, pradziosIndeksas, pradziosIndeksas + kiekis);
        Filmas[] likusiFilmuImtis = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(autoBaze, 0, pradziosIndeksas)),
                        Arrays.stream(Arrays.copyOfRange(autoBaze, pradziosIndeksas + kiekis, autoBaze.length)))
                .toArray(Filmas[]::new);

        Collections.shuffle(Arrays.asList(pradineFilmuImtis)
                .subList(0, (int) (pradineFilmuImtis.length * isbarstKoef)));
        Collections.shuffle(Arrays.asList(likusiFilmuImtis)
                .subList(0, (int) (likusiFilmuImtis.length * isbarstKoef)));

        FilmųGamyba.pradinisIndeksas = 0;
        galinisIndeksas = likusiFilmuImtis.length - 1;
        FilmųGamyba.filmai = likusiFilmuImtis;
        return pradineFilmuImtis;
    }

    public static Filmas gautiIsBazes() throws MyException {
        if ((galinisIndeksas - pradinisIndeksas) < 0) {
            throw new MyException(String.valueOf(galinisIndeksas - pradinisIndeksas), 4);
        }
        // Vieną kartą Filmas imamas iš masyvo pradžios, kitą kartą - iš galo.     
        arPradzia = !arPradzia;
        return filmai[arPradzia ? pradinisIndeksas++ : galinisIndeksas--];
    }
}
