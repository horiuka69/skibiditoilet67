import java.util.HashMap;
import java.util.Map;

// Inventar hrace s omezenou kapacitou
public class Batoh {

    public static final int KAPACITA = 3;
    private Map<String, Predmet> predmety;

    // Vytvori prazdny batoh
    public Batoh() {
        predmety = new HashMap<>();
    }

    // Vlozi predmet do batohu, vraci true pri uspechu
    public boolean vloz(Predmet predmet) {
        if (jePlny()) {
            return false;
        }
        predmety.put(predmet.getNazev(), predmet);
        return true;
    }

    // Vybere predmet z batohu
    public Predmet vyber(String nazev) {
        return predmety.remove(nazev);
    }

    // Zjisti zda batoh obsahuje predmet
    public boolean obsahuje(String nazev) {
        return predmety.containsKey(nazev);
    }

    // Vrati seznam veci v batohu
    public String getSeznamVeci() {
        if (predmety.isEmpty()) {
            return "Batoh je prazdny.";
        }
        return "V batohu mas: " + String.join(", ", predmety.keySet());
    }

    // Zjisti zda je batoh plny
    public boolean jePlny() {
        return predmety.size() >= KAPACITA;
    }
}
