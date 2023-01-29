package Lab2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;

public class Greitaveika {

   Filmas[] filmųBazė1;
	ListKTU<Filmas> aSeries = new ListKTU<>();
	Random ag = new Random();  // atsitiktinių generatorius
//	int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
//    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    int[] tiriamiKiekiai = {1_000, 2_000, 4_000, 8_000};
	void generuotiFilmus(int kiekis) {
		String[][] am = { // galimų filmų ir jų žanrų masyvas
			{"Filmas1", "Drama", "Animacinis", "Siaubo", "Romantinis"},
			{"Filmas13", "Fantastinis", "Dokumentinis", "Drama"},
		};
                String[][] ak = { // galimų studijų ir režisierių masyvas
			{"Studija1", "Režisierius1", "Režisierius12", "Režisierius16"},
			{"Studija10", "Režisierius11", "Režisierius15", "Režisierius14"},
		};
		filmųBazė1 = new Filmas[kiekis];
		ag.setSeed(2016);
		for (int i = 0; i < kiekis; i++) {
			int ma = ag.nextInt(am.length);        // filmo indeksas  0..
			int mo = ag.nextInt(am.length - 1) + 1; // žanro indeksas 1..
                        int mi = ag.nextInt(ak.length); // filmo studijos indeksas 0..
                        int mu = ag.nextInt(ak.length - 1) + 1; // režisieriaus indeksas 1..
			filmųBazė1[i] = new Filmas(am[ma][0], am[ma][mo], ak[mi][0], ak[mi][mu],
					1990 + ag.nextInt(25), // metai tarp 1990 ir 2014
					500 + ag.nextDouble() * 2000); // pelnas tarp 500mln ir 2500mln
		}
		Collections.shuffle(Arrays.asList(filmųBazė1));
		aSeries.clear();
		for (Filmas a : filmųBazė1) {
			aSeries.add(a);
		}
	}

	void paprastasTyrimas(int elementųKiekis) {
		// Paruošiamoji tyrimo dalis
		long t0 = System.nanoTime();
		generuotiFilmus(elementųKiekis);
		ListKTU<Filmas> aSeries2 = aSeries.clone();
		ListKTU<Filmas> aSeries3 = aSeries.clone();
		ListKTU<Filmas> aSeries4 = aSeries.clone();
                ListKTU<Filmas> aSeries5 = aSeries.clone();
		long t1 = System.nanoTime();
		System.gc();
		System.gc();
		System.gc();
		long t2 = System.nanoTime();

		//  Greitaveikos bandymai ir laiko matavimai
		aSeries.sortJava();
		long t3 = System.nanoTime();
		aSeries2.sortJava(Filmas.pagalPelną);
		long t4 = System.nanoTime();
		aSeries3.sortBuble();
		long t5 = System.nanoTime();
		aSeries4.sortBuble(Filmas.pagalPelną);
		long t6 = System.nanoTime();
                aSeries5.MinMax();
                long t7 = System.nanoTime();
		Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
				(t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
				(t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9, 
                                (t7 - t6) / 1e9);
                
	}

	void tyrimoPasirinkimas() {
		long memTotal = Runtime.getRuntime().totalMemory();
		Ks.oun("memTotal= " + memTotal);
		// Pasižiūrime kaip generuoja automobilius (20) vienetų)
		generuotiFilmus(20);
		for (Filmas a : aSeries) {
			Ks.oun(a);
		}
		Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
		Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
		Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
		Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
		Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
		Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
                Ks.oun("7 - Rūšiavimas MinMax metodu");
		Ks.ouf("%6d %7d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6, 7);
		for (int n : tiriamiKiekiai) {
			paprastasTyrimas(n);
		}
		Ks.oun("Rikiavimo metodų greitaveikos tyrimas baigtas.");
		
		// Čia gali būti ir kitokio tyrimo metodo (jūsų sugalvoto) iškvietimas.
	}

    public static void main(String[] args) {
        	Locale.setDefault(new Locale("LT"));
		new Greitaveika().tyrimoPasirinkimas();
    }
    
}
