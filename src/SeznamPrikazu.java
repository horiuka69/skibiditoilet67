import java.util.HashMap;
import java.util.Map;

/**
 * Trida SeznamPrikazu udrzuje vsechny dostupne prikazy ve hre.
 * Slouzi pro vyhledavani a overovani platnosti zadanych prikazu.
 */
public class SeznamPrikazu {

    private Map<String, IPrikaz> prikazy; // Mapa prirazujici nazev k objektu prikazu

    /**
     * Vytvori prazdny seznam prikazu.
     */
    public SeznamPrikazu() {
        prikazy = new HashMap<>();
    }

    /**
     * Vlozi novy prikaz do seznamu.
     * @param prikaz Objekt prikazu k vitozeni.
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        prikazy.put(prikaz.getNazev(), prikaz);
    }

    /**
     * Vyhleda prikaz podle zadaneho retezce.
     * @param retezec Nazev prikazu.
     * @return Objekt prikazu nebo null, pokud neexistuje.
     */
    public IPrikaz vratPrikaz(String retezec) {
        if (prikazy.containsKey(retezec)) {
            return prikazy.get(retezec);
        }
        return null;
    }

    /**
     * Overuje, zda je dany retezec platnym prikazem.
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return prikazy.containsKey(retezec);
    }

    /**
     * Vraci textovy seznam nazvu vsech registrovanych prikazu.
     */
    public String vratNazvyPrikazu() {
        return String.join(" ", prikazy.keySet());
    }
}
