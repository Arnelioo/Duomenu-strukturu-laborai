package VizualiosStrukturos;

import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 * Klasė veiksmams su automobiliais atlikti.
 */
public class TaxiSkaičiavimai {
    
    private final List<Taxi> automobiliai = new ArrayList<>();
    
    /**
     * Failo skaitymas ir jo turinio išvedimas į JTextArea
     * @param fName failo vardas
     * @param ta JTextArea klasės objektas
     */
    public String loadAndPrint(File fName, JTextArea ta) {
		String klaidosKodas = null;
		try {
			automobiliai.clear();
			BufferedReader fReader =  new BufferedReader(new FileReader(fName));
			String line;
			ta.append("Duomenys iš failo <" + fName.getName() + ">:\n\n");
                        ta.append("Vairuotojas  Markė Vietos Metai Spalva   Rida" + 
                                "\n---------------------------------------------\n");
			while ((line = fReader.readLine()) != null) {
				automobiliai.add(new Taxi(line));
				ta.append(line + "\n");
			}
			fReader.close();
		} catch (IOException e) {
			klaidosKodas = "Failo " + fName.getName() + " skaitymo klaida";
		}
		return klaidosKodas;
	}
    
    /**
     * Rikuoja pradinį sąrašą pagal ridą didėjančia tvarka.
     */
    public void rikiuojaPagalRidą() {
		Collections.sort(automobiliai, Taxi.pagalRida);		
	}
    
    /**
     * Taxi masyve suranda daugiausia eksploatuotą automobilį ir grąžina objektą.
     * @return klasės objektą.
     */
    public Taxi didžiausiaRida(){
        Taxi rida = new Taxi();
        for(int i = 0; i < automobiliai.size(); i++)
        {
            rida = Collections.max(automobiliai, Taxi.pagalRida);
        }
        return rida;
    }

    /**
     * Atrenka automobilius pagal nurodytą markę
     * @param markė String klasės objektas
     * @param ta JTextArea klasės objektas
     */
    public void atrinktiPagalMarkę(String markė, JTextArea ta) {
		for (Taxi a: automobiliai){
			if (a.getMarkė().startsWith(markė)) {
				ta.append(a.toString() + "\n");
			}
		}
    }
    
    /**
     * Užkloja ArrayList kolekcijos metodą toString
     * @return automobilio objektą išvedimui
     */
    @Override
    public String toString() {
		String kolekcija = "";
        for (Taxi a: automobiliai) {
	        kolekcija += a.toString() + "\n";
		}
		return kolekcija;
    };
}
