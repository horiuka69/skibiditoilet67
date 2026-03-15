import java.util.HashMap;
import java.util.Map;

/**
 * Inventar hrace s omezenou kapacitou.
 * Slouzi pro spravu predmetu, ktere hrac nese u sebe.
 */
public class Batoh {

    public static final int KAPACITA = 3; // Maximalni pocet predmetu v batohu
    private Map<String, Predmet> predmety; // Mapa predmetu v batohu

    /**
     * Vytvori prazdny batoh (inicializace mapy).
     */
    public Batoh() {
        predmety = new HashMap<>();
    }

    /**
     * Vlozi predmet do batohu, pokud neni plny.
     * @param predmet Predmet ke vlozeni.
     * @return true, pokud se vlozeni podarilo.
     */
    public boolean vloz(Predmet predmet) {
        if (jePlny()) {
            return false;
        }
        predmety.put(predmet.getNazev(), predmet);
        return true;
    }

    /**
     * Vyjme predmet z batohu podle nazvu.
     * @param nazev Nazev predmetu k vyjmuti.
     * @return Vyjmuty predmet nebo null, pokud tam neni.
     */
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

    /**
     * Kontroluje, zda batoh obsahuje predmet daneho nazvu.
     */
    public boolean obsahuje(String nazev) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazev)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vraci textovy seznam vsech predmetu v batohu.
     */
    public String getSeznamVeci() {
        if (predmety.isEmpty()) {
            return "Batoh je prazdny.";
        }
        return "V batohu mas: " + String.join(", ", predmety.keySet());
    }

    /**
     * Vraci referenci na predmet v batohu bez jeho vyjmuti.
     */
    public Predmet getPredmet(String nazev) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazev)) {
                return predmety.get(klic);
            }
        }
        return null;
    }

    /**
     * Kontroluje, zda je dosazeno maximalni kapacity batohu.
     */
    public boolean jePlny() {
        return predmety.size() >= KAPACITA;
    }
}
