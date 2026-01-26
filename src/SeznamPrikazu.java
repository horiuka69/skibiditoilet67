import java.util.HashMap;
import java.util.Map;

// Spravce vsech platnych prikazu (Invoker v Command patternu)
public class SeznamPrikazu {

    private Map<String, IPrikaz> prikazy;

    // Vytvori prazdny seznam prikazu
    public SeznamPrikazu() {
        prikazy = new HashMap<>();
    }

    // Vlozi prikaz do seznamu
    public void vlozPrikaz(IPrikaz prikaz) {
        prikazy.put(prikaz.getNazev(), prikaz);
    }

    // Vrati prikaz podle nazvu
    public IPrikaz vratPrikaz(String retezec) {
        if (prikazy.containsKey(retezec)) {
            return prikazy.get(retezec);
        }
        return null;
    }

    // Zjisti zda je prikaz platny
    public boolean jePlatnyPrikaz(String retezec) {
        return prikazy.containsKey(retezec);
    }

    // Vrati nazvy vsech prikazu
    public String vratNazvyPrikazu() {
        return String.join(" ", prikazy.keySet());
    }
}
