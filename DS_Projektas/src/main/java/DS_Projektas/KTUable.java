package DS_Projektas;

public interface KTUable<T> extends Comparable<T> {
    KTUable create(String dataString); // sukuria naują objektą iš eilutės
                    // patikrina objekto reikšmes
    void parse(String dataString);     // suformuoja objektą iš eilutės
   // public int compare(Object o1, Object o2);
 
}
