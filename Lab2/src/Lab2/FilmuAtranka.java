package Lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import studijosKTU.*;

/**
 * Klasė veiksmams su filmais atlikti.
 */
public class FilmuAtranka {
    
    private final ListKTUx<Filmas> filmai = new ListKTUx<>(new Filmas());
    
    /**
     * Atrenka filmus pagal nurodytą žanrą
     * @param žanras filmo žanras
     * @param ta JTextArea klasės objektas
     */
    public void atrinktiPagalŽanrą(String žanras, JTextArea ta) {
		for (Filmas a: filmai){
			if (a.getŽanras().startsWith(žanras)) {
				ta.append(a.toString() + "\n");
			}
		}
    }
    
    /**
     * Atrenka filmus pagal nurodytą pelno intervalą
     * @param riba1 pirmoji riba
     * @param riba2 antroji riba
     * @param ta JTextArea klasės objektas
     */
    public void atrinktiPagalPelną(int riba1, int riba2, JTextArea ta) {
		for (Filmas a: filmai){
			if (a.getPelnas() >= riba1 && a.getPelnas() <= riba2) {
				ta.append(a.toString() + "\n");
			}
		}
    }
        
    /**
     * Failo skaitymas ir jo turinio išvedimas į JTextArea
     * @param fName failo vardas
     * @param ta JTextArea klasės objektas
     * @return klaidos pranešimą
     */
        public String loadAndPrint(File fName, JTextArea ta) {
		String klaidosKodas = null;
		try {
			filmai.clear();
			BufferedReader fReader =  new BufferedReader(new FileReader(fName));
			String line;
			ta.append("Duomenys iš failo <" + fName.getName() + ">:\n\n");
                        ta.append("Filmo pav.  Žanras     Filmo studija  Režisierius    Metai Pelnas(mln)" + 
                                "\n----------------------------------------------------------------------\n");
			while ((line = fReader.readLine()) != null) {
				filmai.add(new Filmas(line));
				ta.append(line + "\n");
			}
			fReader.close();
		} catch (IOException e) {
			klaidosKodas = "Failo " + fName.getName() + " skaitymo klaida";
		}
		return klaidosKodas;
	}
        
        /**
         * Rikuoja pradinį sąrašą pagal pelną
         */
        public void rikiuojaPagalPelną() {
		filmai.sortJava(Filmas.pagalPelną);		
	}
        
        /**
         * Rikuoja pradinį sąrašą pagal žanrą
         */
        public void rikiuojaPagalŽanrą() {
		filmai.sortJava(Filmas.pagalŽanrą);			
	}
        
        /**
         * Rikuoja pradinį sąrašą pagal išleidimo metus ir pelną
         */
        public void rikiuojaPagalMetusIrPelną() {
		filmai.sortJava(Filmas.pagalMetusIrPelną);		
	}
        
        /**
        * Užkloja ArrayList kolekcijos metodą toString
        * @return automobilio objektą išvedimui
        */
        @Override
        public String toString() {
		String kolekcija = "";
        for (Filmas a: filmai) {
	        kolekcija += a.toString() + "\n";
		}
		return kolekcija;
    };
}
