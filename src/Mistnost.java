import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Třída představující místnost (lokaci) ve hře.
public class Mistnost {

    private String nazev;
    private String popis;
    private Map<String, Mistnost> vychody; // sousedni mistnosti

    public Mistnost(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashMap<>(); // Inicializace mapy
    }

    public void setVychod(Mistnost vedlejsi) {
        if (vedlejsi != null) {
            vychody.put(vedlejsi.getNazev(), vedlejsi);
        }
    }

    public String getNazev() {
        return nazev;
    }

    public String getDlouhyPopis() {
        return "Jsi v mistnosti/lokaci " + nazev + ".\n"
                + popis + "\n"
                + "Vychody: " + vychody.keySet().stream().collect(Collectors.joining(", "));
    }

    public Mistnost vratVychod(String nazevSouseda) {
        return vychody.get(nazevSouseda);
    }

    public Collection<Mistnost> getVychody() {
        return Collections.unmodifiableCollection(vychody.values());
    }

    // Prozatimni implementace pro predmety a postavy (aby kod byl kompilovatelny,
    // ale zatim neresime logiku)
    public void vlozPredmet(Predmet predmet) {
        // TODO: Implementovat inventar mistnosti
    }

    public Predmet vezmiPredmet(String nazevPredmetu) {
        return null; // TODO
    }

    public boolean obsahujePredmet(String nazevPredmetu) {
        return false; // TODO
    }

    public void vlozPostavu(Postava postava) {
        // TODO
    }

    public Postava getPostava(String jmeno) {
        return null; // TODO
    }
}
