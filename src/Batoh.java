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

    // Vlozi predmet do batohu
    public boolean vloz(Predmet predmet) {
        if (jePlny()) {
            return false;
        }
        predmety.put(predmet.getNazev(), predmet);
        return true;
    }

    public Predmet vyber(String nazev) {
        return predmety.remove(nazev);
    }

    public boolean obsahuje(String nazev) {
        return predmety.containsKey(nazev);
    }

    public String getSeznamVeci() {
        if (predmety.isEmpty()) {
            return "Batoh je prazdny.";
        }
        return "V batohu mas: " + String.join(", ", predmety.keySet());
    }

    public Predmet getPredmet(String nazev) {
        return predmety.get(nazev);
    }

    public boolean jePlny() {
        return predmety.size() >= KAPACITA;
    }
}
