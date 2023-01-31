package DS_Projektas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
* Klasė duomenų apie filmą skaitymo ir spausdinimo metodams realizuoti
*/
public class FilmuAtranka{
    /*
    * Metodas skirtas pasirinkto failo duomenims skaityti bei sudėti į listą
    */
        public static UnrolledLinkedList<Filmas> loadAndPrint(File fName, JTextArea ta) {
           UnrolledLinkedList<Filmas> naujas1 = new UnrolledLinkedList<Filmas>();
		try {
			BufferedReader fReader =  new BufferedReader(new FileReader(fName));
			String line;
			ta.append("Duomenys įkelti iš failo <" + fName.getName() + ">:\n");
			while ((line = fReader.readLine()) != null) {
				naujas1.add(new Filmas(line));
			}
                        ta.append(" ");
			fReader.close();
		} catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
					"Failo " + fName.getName() + " skaitymo klaida",
					"Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
		}
		return naujas1;   
	}
       
        /**
        * Metodas skirtas duomenų spausinimui į sąsajos langą
        * @param filmuList
        * @param tekstas 
        */
       public static void PrintList(UnrolledLinkedList<Filmas> filmuList, JTextArea tekstas)
       {
       String top =
       """
       -----------------------------------------------------------------------------------
       Nr.  Pavadinimas      Žanras      Studija   Režisierius     Išl. Metai   Pelnas        
       -----------------------------------------------------------------------------------""";
       tekstas.append(top + "\n");
       for (int i = 0; i < filmuList.size(); i++)
       {
            Filmas com = filmuList.get(i);
            tekstas.append((i + 1) + com.toString());
       }
       tekstas.append("-----------------------------------------------------------------------------------\n");
       }
       
       public static void main(String[] args) {
       //testavimui
           UnrolledLinkedList list = new UnrolledLinkedList(20);
           list.add(new Filmas("Filmas1", "Drama", "Studija3", "Režisierius5", 2009, 568.0));
           list.add(new Filmas("Filmas3", "Veiksmo", "Studija4", "Režisierius4", 2012, 957.5));
           list.add(new Filmas("Filmas6", "Siaubo", "Studija8", "Režisierius1", 2020,762.5));
           Ks.oun(list);
           list.remove(2);
           Ks.oun(list);
           list.add(new Filmas("Filmas10", "Nuotykių", "Studija5", "Režisierius3", 2019,652.5));
           Ks.oun(list);
           if(list.containsAll(list))
           Ks.oun("Yra");
           else
               Ks.oun("Nėra");
           list.sortJava(Filmas.pagalPelną);
           Ks.oun(list);
       }
}
