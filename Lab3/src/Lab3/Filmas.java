package Lab3;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import laborai.studijosktu.*;

/**
 * Klasė filmo duomenims aprašyti.
 */
public final class Filmas implements KTUable<Filmas> {

    // bendri duomenys visiems filmams (visai klasei)
    private static final int priimtinųMetųRiba = 1990;
    private static final int esamiMetai = LocalDate.now().getYear();
    private static final String idCode = "TA";   //  ***** nauja
    private static int serNr = 100;               //  ***** nauja
    private final String filmoRegNr;
    
    private String filmoPav = "";
    private String žanras = "";
    private String studija = "";
    private String režisierius = "";
    private double pelnas = -1.0;
    private int išleidimoMetai = -1;

    public Filmas() {
        filmoRegNr = idCode + (serNr++);    // suteikiamas originalus filmoRegNr
    }

    public Filmas(String pavadinimas, String žanras, String studija,
            String režisierius, int išleidimoMetai, double pelnas) {
        filmoRegNr = idCode + (serNr++);    // suteikiamas originalus filmoRegNr
        this.filmoPav = pavadinimas;
        this.žanras = žanras;
        this.studija = studija;
        this.režisierius = režisierius;
        this.išleidimoMetai = išleidimoMetai;
        this.pelnas = pelnas;
        validate();
    }

    public Filmas(String dataString) {
        filmoRegNr = idCode + (serNr++);    // suteikiamas originalus filmoRegNr
        this.parse(dataString);
    }

    public Filmas(Builder builder) {
        filmoRegNr = idCode + (serNr++);    // suteikiamas originalus filmoRegNr
        this.filmoPav = builder.filmoPav;
        this.žanras = builder.žanras;
        this.studija = builder.studija;
        this.režisierius = builder.režisierius;
        this.išleidimoMetai = builder.išleidimoMetai;
        this.pelnas = builder.pelnas;
        validate();
    }

    @Override
    public Filmas create(String dataString) {
        return new Filmas(dataString);
    }

   /**
     * Patikrina ar filmai atitinka reikalavimą
     * @return klaidos pranešimą
     */
    @Override
	public String validate() {
		String klaidosTipas = "";
		if (išleidimoMetai < priimtinųMetųRiba || išleidimoMetai > esamiMetai) {
			klaidosTipas = "Netinkami išleidimo metai, turi būti ["
					+ priimtinųMetųRiba + ":" + esamiMetai + "]";
		}
		return klaidosTipas;
	}

    @Override
    public final void parse(String dataString) {
        try {
            Scanner ed = new Scanner(dataString);
            filmoPav   = ed.next();
            žanras = ed.next();
            studija = ed.next();
            režisierius = ed.next();
            išleidimoMetai = ed.nextInt();
            pelnas = ed.nextDouble();
        } catch (InputMismatchException  e) {
            System.err.println("Blogas duomenų formatas apie filmą -> " + dataString);
        } catch (NoSuchElementException e) {
            System.err.println("Trūksta duomenų apie filmą -> " + dataString);
        }
    }

    @Override
    public String toString() {  // papildyta su filmoRegNr
        return getFilmoRegNr() + "=" + filmoPav + "_" + žanras + "_" + studija + "_" + režisierius + ":" + išleidimoMetai + " " + String.format("%4.1f", pelnas) ;
    }

    public String getFilmoPav() {
        return filmoPav;
    }

    public String getŽanras() {
        return žanras;
    }
    
    public String getStudija() {
        return studija;
    }
    
    public String getRežisierius() {
        return režisierius;
    }

    public double getPelnas() {
        return pelnas;
    }

    public int getIšleidimoMetai() {
        return išleidimoMetai;
    }

    public String getFilmoRegNr() {  //** nauja.
        return filmoRegNr;
    }

    @Override
    public int compareTo(Filmas a) {
        return getFilmoRegNr().compareTo(a.getFilmoRegNr());
    }

    public static Comparator<Filmas> pagalŽanrą = (Filmas a1, Filmas a2) -> a1.žanras.compareTo(a2.žanras); // pradžioje pagal markes, o po to pagal modelius

    public static Comparator<Filmas> pagalPelną = (Filmas a1, Filmas a2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (a1.pelnas < a2.pelnas) {
            return -1;
        }
        if (a1.pelnas > a2.pelnas) {
            return +1;
        }
        return 0;
    };

    public static Comparator<Filmas> pagalMetusIrPelną = (Filmas a1, Filmas a2) -> {
        // metai mažėjančia tvarka, esant vienodiems lyginamas pelnas
        if (a1.išleidimoMetai > a2.išleidimoMetai) {
            return +1;
        }
        if (a1.išleidimoMetai < a2.išleidimoMetai) {
            return -1;
        }
        if (a1.pelnas > a2.pelnas) {
            return +1;
        }
        if (a1.pelnas < a2.pelnas) {
            return -1;
        }
        return 0;
    };

    // Filmas klases objektų gamintojas
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] ŽANRAI = { // galimų filmų ir jų žanrų masyvas
            {"Filmas1", "Drama", "Animacinis", "Siaubo", "Romantinis"},
            {"Filmas5", "Komedija", "Superherojų", "Drama", "Siaubo"},
            {"Filmas7", "Dokumentinis", "Nuotykių", "Veiksmo", "Drama"},
            {"Filmas13", "Fantastinis", "Dokumentinis", "Drama"},
        };
        private final static String[][] REŽISIERIAI = { // galimų studijų ir režisierių masyvas
            {"Studija1", "Režisierius1", "Režisierius12", "Režisierius16"},
            {"Studija6", "Režisierius4", "Režisierius3", "Režisierius2"},
            {"Studija4", "Režisierius2", "Režisierius6", "Režisierius5"},
            {"Studija10", "Režisierius11", "Režisierius15", "Režisierius14"},
        };

        private String filmoPav = "";
        private String žanras = "";
        private String studija = "";
        private String režisierius = "";
        private double pelnas = -1.0;
        private int išleidimoMetai = -1;

        public Filmas build() {
            return new Filmas(this);
        }

        public Filmas buildRandom() {
            int ma = RANDOM.nextInt(ŽANRAI.length);        // filmo indeksas  0..
            int mo = RANDOM.nextInt(ŽANRAI[ma].length - 1) + 1; // žanro indeksas 1..   
            int mi = RANDOM.nextInt(REŽISIERIAI.length);   // filmo studijos indeksas 0..
            int mu = RANDOM.nextInt(REŽISIERIAI[mi].length - 1) + 1; // režisieriaus indeksas 1..
            return new Filmas(ŽANRAI[ma][0], ŽANRAI[ma][mo], REŽISIERIAI[mi][0], REŽISIERIAI[mi][mu],
                    1990 + RANDOM.nextInt(25),// metai tarp 1990 ir 2009
                    500 + RANDOM.nextDouble() * 2000);// pelnas tarp 800 ir 88800
        }

        public Builder filmoPav(String filmoPav) {
            this.filmoPav = filmoPav;
            return this;
        }

        public Builder žanras(String žanras) {
            this.žanras = žanras;
            return this;
        }

        public Builder studija(String studija) {
            this.studija = studija;
            return this;
        }
        
        public Builder režisierius(String režisierius) {
            this.režisierius = režisierius;
            return this;
        }

        public Builder pelnas(double pelnas) {
            this.pelnas = pelnas;
            return this;
        }

        public Builder išleidimoMetai(int išleidimoMetai) {
            this.išleidimoMetai = išleidimoMetai;
            return this;
        }
    }
}
