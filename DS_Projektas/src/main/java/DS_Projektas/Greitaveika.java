package DS_Projektas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Klasė greitaveikos tyrimui atlikti
 */
public class Greitaveika {
    List<Integer> iSeries = new ArrayList<>();
        UnrolledLinkedList<Integer> list = new UnrolledLinkedList<>(1000);
        UnrolledLinkedList<Integer> list2 = new UnrolledLinkedList<>(1000);
        int[] tiriamiKiekiai = {1000, 2000, 4000, 8000};
//	int[] tiriamiKiekiai = {2000, 4000, 8000, 16000};
//        int[] tiriamiKiekiai = {4000, 8000, 16000, 32000};
    private Comparator<Integer> compare;
	void paprastasTyrimas(int elementųKiekis) {
		// Paruošiamoji tyrimo dalis
                for (int i = 0; i < elementųKiekis; i++)
                    iSeries.add(i);
		Collections.shuffle(Arrays.asList(iSeries));
		for(int i = 0; i < iSeries.size(); i++)
                    list2.add(i);
               
                   long t0 = System.nanoTime();
                for(int i = 0; i < list.size(); i++)
                    list.contains(i);
                
		long t1 = System.nanoTime();
		for(int i = 0; i < iSeries.size(); i++)
                    list.add(i);
		
                long t2 = System.nanoTime();
		for(int i = 0; i < list.size(); i++)
                    list.remove(i);

                long t3 = System.nanoTime();
                for(int i = 0; i < list2.size(); i++)
                    list.sortJava(compare);
                long t4 = System.nanoTime();
		String results = String.format("%7d %7.4f %7.4f %7.4f %7.4f\n",
                        elementųKiekis,(t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
				(t4 - t3) / 1e9);
                System.out.println(results);
	}
        
	void testas() {
		long memTotal = Runtime.getRuntime().totalMemory();
		System.out.println("memTotal= " + memTotal);

		System.out.println("1 - Contains metodas - tikrina ar yra toks sąraše");
		System.out.println("2 - Add metodas - prideda naują elementą listo pabaigoje");
		System.out.println("3 - Remove metodas - sąrašo elementų panaikinimas");
                System.out.println("4 - SortJava metodas - sąrašo rikiavimas");
		String line = String.format(" %12d %7d %7d %7d\n", 1, 2, 3, 4);
                System.out.println(line);
		for (int n : tiriamiKiekiai) {
			paprastasTyrimas(n);
		}
		System.out.println("Greitaveikos tyrimas baigtas.");
	}

	public static void main(String[] args) {
		// suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
		Locale.setDefault(new Locale("LT"));
		new Greitaveika().testas();
	}
}
