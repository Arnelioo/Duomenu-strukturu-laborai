package VizualiosStrukturos;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Klasė taxi automobilio duomenims aprašyti.
 */
public class Taxi {

    private String vairuotojas;
    private String markė;
    private String spalva;
    private int sėdimosVietos;
    private int pagamMetai;
    private int rida;
    
    public Taxi(){
    }
    
    /**
     * Konstruktorius su parametrais.
     * @param pavardė vairuotojo pavardė.
     * @param markė automobilio markė.
     * @param vietos sėdimų vietų skaičius.
     * @param metai pagaminimo metai.
     * @param spalva automobilio spalva.
     * @param rida rida.
     */
    public Taxi(String pavardė, String markė, int vietos, int metai, String spalva,  int rida){
        this.vairuotojas = pavardė;
        this.markė = markė;
        this.spalva = spalva;
        this.sėdimosVietos = vietos;
        this.pagamMetai = metai;
        this.rida = rida;
    }
    public Taxi(String dataString){
        this.parse(dataString);
    }

    public Taxi create(String dataString) {
        Taxi a = new Taxi();
        a.parse(dataString);
        return a;
    }
    
    public final void parse(String dataString) {
        try { 
            Scanner ed = new Scanner(dataString);
            vairuotojas   = ed.next();
            markė = ed.next();
            sėdimosVietos= ed.nextInt();
            pagamMetai    = ed.nextInt();
            spalva = ed.next();
            rida = ed.nextInt();
        } catch (InputMismatchException  e) {
            System.err.println("Blogas duomenų formatas apie taxi -> " + dataString);
        } catch (NoSuchElementException e) {
            System.err.println("Trūksta duomenų apie taxi -> " + dataString);
        }
    }
    
    /**
     * Užklotas operatorius.
     * @return suformuotą eilutę.
     */
    @Override
    public String toString(){ 
        return String.format("%-12s %-8s %-8s %-10s %-8s %-3s",
               vairuotojas, markė, sėdimosVietos, pagamMetai, spalva, rida);
    };
    
    public String getVairuotojas(){
        return vairuotojas;
    }
    public String getMarkė(){
        return markė;
    }
    public int getSėdimosVietos(){
        return sėdimosVietos;
    }
    public int getPagamMetai(){
        return pagamMetai;
    }
    public String getSpalva(){
        return spalva;
    }
    public int getRida(){
        return rida;
    }
    
    /**
     * Metodas rikiavimui didėjimo tvarka.
     */
    public final static Comparator pagalRida = new Comparator() {
    @Override
    public int compare(Object o1, Object o2) {
          double k1 = ((Taxi) o1).getRida();
          double k2 = ((Taxi) o2).getRida();
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0; 
       }
    };
}
