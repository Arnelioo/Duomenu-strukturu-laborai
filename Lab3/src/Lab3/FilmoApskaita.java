package Lab3;

import laborai.studijosktu.*;

public class FilmoApskaita {

    public static SetADT<String> filmųŽanrai(Filmas[] filmas) {
        SetADT<Filmas> uni = new BstSetKTU<>(Filmas.pagalŽanrą);
        SetADT<String> kart = new BstSetKTU<>();
        for (Filmas a : filmas) {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size()) {
                kart.add(a.getŽanras());
            }
        }
        return kart;
    }
}
