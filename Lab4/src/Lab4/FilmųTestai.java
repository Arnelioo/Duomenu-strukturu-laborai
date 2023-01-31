package Lab4;

import laborai.studijosktu.*;
import java.util.Locale;



public class FilmųTestai {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        atvaizdzioTestas();
        //greitaveikosTestas();
        
        
        
    }

    public static void atvaizdzioTestas() {
        Filmas a1 = new Filmas("Filmas1", "Drama", "Studija1", "Režisierius1", 2000, 460.3);
        Filmas a2 = new Filmas("Filmas2", "Dokumentinis", "Studija2", "Režisierius2", 2013, 723.0);
        Filmas a3 = new Filmas("Filmas3", "Siaubo", "Studija4", "Režisierius3", 2011, 553.0);
        Filmas a4 = new Filmas("Filmas4 Veiksmo Studija3 Režisierius4 1997 635.0");
        Filmas a5 = new Filmas.Builder().buildRandom();
        Filmas a6 = new Filmas("Filmas7 Siaubo Studija2 Režisierius6 2016 368.0");
        Filmas a7 = new Filmas("Filmas10 Veiksmo Studija3 Režisierius4 2003 865.0");

        // Raktų masyvas
        String[] autoId = {"TA156", "TA102", "TA178", "TA171", "TA105", "TA106", "TA107", "TA108"};
        int id = 0;
        MapKTUx<String, Filmas> atvaizdis
                = new MapKTUx(new String(), new Filmas(), HashType.DIVISION);
        // Reikšmių masyvas
        Filmas[] auto = {a1, a2, a3, a4, a5, a6, a7};
        for (Filmas a : auto) {
            atvaizdis.put(autoId[id++], a);
        }
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(atvaizdis.contains(autoId[6]));
        Ks.oun(atvaizdis.contains(autoId[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(atvaizdis.remove(autoId[1]));
        Ks.oun(atvaizdis.remove(autoId[7]));
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(atvaizdis.get(autoId[2]));
        Ks.oun(atvaizdis.get(autoId[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(atvaizdis);
    }

    //Konsoliniame režime
    private static void greitaveikosTestas() {
        System.out.println("Greitaveikos tyrimas:\n");
        Greitaveika gt = new Greitaveika();
        //Šioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(),
                "Greitaveikos_tyrimo_gija").start();
        try {
            String result;
            while (!(result = gt.getResultsLogger().take())
                    .equals(Greitaveika.FINISH_COMMAND)) {
                System.out.println(result);
                gt.getSemaphore().release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gt.getSemaphore().release();
    }
}
    
