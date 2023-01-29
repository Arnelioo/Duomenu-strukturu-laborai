package Lab2;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Comparator;
import studijosKTU.*;

/**
 * Klasė filmo duomenims aprašyti.
 */
public class Filmas implements KTUable<Filmas>{

    final static private int priimtinųMetųRiba = 1999;
    final static private int esamiMetai = LocalDate.now().getYear();
    
    private String filmoPav;
    private String žanras;
    private String studija;
    private String režisierius;
    private double pelnas;
    private int išleidimoMetai;
   
    
    public Filmas(){
    }
    /**
     * 
     * @param pavad filmo pavadinimas
     * @param žanras filmo žanras
     * @param studija filmo studija
     * @param režisierius filmo režisierius
     * @param metai išleidimo metai
     * @param pelnas pelnas
     */
    public Filmas(String pavad, String žanras, String studija, String režisierius,
            int metai, double pelnas){
        this.filmoPav = pavad;
        this.žanras = žanras;
        this.studija = studija;
        this.režisierius = režisierius;
        this.išleidimoMetai = metai;
        this.pelnas = pelnas;
    }
    
    public Filmas(String dataString){
        this.parse(dataString);
    }
    
    public Filmas create(String dataString) {
        Filmas a = new Filmas();
        a.parse(dataString);
        return a;
    }
    
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
    
     /**
     * Užklotas operatorius.
     * @return suformuotą eilutę.
     */
    @Override
	public String toString() {
		return String.format("%-10s %-14s %-11s %-14s %-6s %-8s %s",
				filmoPav, žanras, studija, režisierius,
                                išleidimoMetai, pelnas, validate());
	}
        
    public String getFilmoPav() {
        return filmoPav;
    }
    public String getŽanras() {
        return žanras;
    }
    public String getStudija(){
        return studija;
    }
    public String getGamMetai() {
        return režisierius;
    }
    public int getIšleidimoMetai() {
        return išleidimoMetai;
    }
    public double getPelnas() {
        return pelnas;
    }
    
        @Override
	public int compareTo(Filmas a) {
		double kainaKita = a.getPelnas();
		return Double.compare(kainaKita, pelnas);
	}
        
        /**
         * Metodas rikiuoti pagal žanrą
         */
        public final static Comparator<Filmas> pagalŽanrą = new Comparator<Filmas>() {
		@Override
		public int compare(Filmas a1, Filmas a2) {
			int cmp = a1.getŽanras().compareTo(a2.getŽanras());
			return cmp;
		}
	};
        
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
         * Metodas rikaivimui pagal išleidimo metus ir pelną
         */
     public final static Comparator pagalMetusIrPelną = new Comparator() {
       @Override
       public int compare(Object o1, Object o2) {
          Filmas a1 = (Filmas) o1;
          Filmas a2 = (Filmas) o2;
          if(a1.getIšleidimoMetai() < a2.getIšleidimoMetai()) return 1;
          if(a1.getIšleidimoMetai() > a2.getIšleidimoMetai()) return -1;
          if(a1.getPelnas() < a2.getPelnas()) return 1;
          if(a1.getPelnas() > a2.getPelnas()) return -1;
          return 0;
       }
    };
}
