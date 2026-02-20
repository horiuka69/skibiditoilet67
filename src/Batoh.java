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
        String klicKNalezeni = null;
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazev)) {
                klicKNalezeni = klic;
                break;
            }
        }
        return klicKNalezeni != null ? predmety.remove(klicKNalezeni) : null;
    }

    public boolean obsahuje(String nazev) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazev)) {
                return true;
            }
        }
        return false;
    }

    public String getSeznamVeci() {
        if (predmety.isEmpty()) {
            return "Batoh je prazdny.";
        }
        return "V batohu mas: " + String.join(", ", predmety.keySet());
    }

    public Predmet getPredmet(String nazev) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazev)) {
                return predmety.get(klic);
            }
        }
        return null;
    }

    public boolean jePlny() {
        return predmety.size() >= KAPACITA;
    }
}
