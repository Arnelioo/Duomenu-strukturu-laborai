package DS_Projektas;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Klasė filmo duomenims aprašyti
 */
public class Filmas{
    
    //Filmo kintamieji
    private String filmoPav;
    private String žanras;
    private String studija;
    private String režisierius;
    private double pelnas;
    private int išleidimoMetai;

	public Filmas() {
	}

	public Filmas(String pavadinimas, String zanras,
			String studija, String rezisierius, int metai, double pelnas) {
		this.filmoPav = pavadinimas;
		this.žanras = zanras;
		this.studija = studija;
		this.režisierius = rezisierius;
		this.pelnas = pelnas;
                this.išleidimoMetai = metai;
	}

	public Filmas(String dataString) {
            this.parse(dataString);
	}

	/**
	 * Sukuria naują objektą iš eilutės
	 * @param dataString eilutė objekto sukūrimui
	 * @return Automobilio klasės objektą
	 */
	public Filmas create(String dataString) {
            Filmas a = new Filmas();
            a.parse(dataString);
            return a;
	}

	/**
	 * Suformuoja objektą iš eilutės
	 * @param dataString eilutė objektui formuoti
	 */
	public final void parse(String dataString) {
		try {   
			Scanner ed = new Scanner(dataString); // numatytieji skirtukai: tarpas, tab, eilutės pabaiga
			// Skiriklius galima pakeisti Scanner klasės metodu useDelimitersr 
			//	Pavyzdžiui, ed.useDelimiter(", *"); reikštų, kad skiriklis bus kablelis ir vienas ar daugiau tarpų.
			filmoPav = ed.next();
			žanras = ed.next();
			studija = ed.next();
			režisierius = ed.next();
                        išleidimoMetai = ed.nextInt();
                        pelnas = ed.nextDouble();
		} catch (InputMismatchException e) {
			Ks.ern("Blogas duomenų formatas apie auto -> " + dataString);
		} catch (NoSuchElementException e) {
			Ks.ern("Trūksta duomenų apie auto -> " + dataString);
		}
	}
        
         /**
         * Metodas rikiuoti pagal pelną
         */
        public final static Comparator<Filmas> pagalPelną = new Comparator<Filmas>() {
		@Override
		public int compare(Filmas o1, Filmas o2) {
			double k1 = o1.getPelnas();
			double k2 = o2.getPelnas();
			if (k1 < k2) return -1;
			if (k1 > k2) return 1;
			return 0;
		}
	};

	/**
	 * Objekto reikšmių išvedimas, nurodant išvedime tik objekto vardą
	 * @return Išvedimui suformuota eilutė
	 */
	@Override
	public String toString() {  // surenkama visa reikalinga informacija
                return String.format("| %10s | %15s | %8s | %8s | %8s | %8s | %n", filmoPav, žanras, studija, režisierius,išleidimoMetai, pelnas);
	}
	
         public String getPavadinimas() {
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
        
        public int getMetai() {
		return išleidimoMetai;
	}
}
