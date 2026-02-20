import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Mistnost ve hre
public class Mistnost {

    private String nazev;
    private String popis;
    private Map<String, Mistnost> vychody;
    private Map<String, Predmet> predmety;
    private Map<String, Postava> postavy;

    // Vytvori mistnost
    public Mistnost(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
        this.postavy = new HashMap<>();
    }

    // Nastavi vychod do vedlejsi mistnosti
    public void setVychod(Mistnost vedlejsi) {
        if (vedlejsi != null) {
            vychody.put(vedlejsi.getNazev(), vedlejsi);
        }
    }

    // Vrati nazev mistnosti
    public String getNazev() {
        return nazev;
    }

    // Vrati detailni popis mistnosti s vychody, predmety a postavami
    public String getDlouhyPopis() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jsi v mistnosti/lokaci ").append(nazev).append(".\n");
        sb.append(popis).append("\n");
        sb.append("Vychody: ").append(vychody.keySet().stream().collect(Collectors.joining(", ")));

        if (!predmety.isEmpty()) {
            sb.append("\nPredmety: ").append(predmety.keySet().stream().collect(Collectors.joining(", ")));
        }

        if (!postavy.isEmpty()) {
            sb.append("\nPostavy: ").append(postavy.keySet().stream().collect(Collectors.joining(", ")));
        }

        return sb.toString();
    }

    // Vrati mistnost v danem smeru
    public Mistnost vratVychod(String nazevSouseda) {
        for (String klic : vychody.keySet()) {
            if (klic.equalsIgnoreCase(nazevSouseda)) {
                return vychody.get(klic);
            }
        }
        return null;
    }

    // Vrati vsechny vychody
    public Collection<Mistnost> getVychody() {
        return Collections.unmodifiableCollection(vychody.values());
    }

    // Vlozi predmet do mistnosti
    public void vlozPredmet(Predmet predmet) {
        if (predmet != null) {
            predmety.put(predmet.getNazev(), predmet);
        }
    }

    // Vezme predmet z mistnosti
    public Predmet vezmiPredmet(String nazevPredmetu) {
        String klicKNalezeni = null;
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazevPredmetu)) {
                klicKNalezeni = klic;
                break;
            }
        }
        return klicKNalezeni != null ? predmety.remove(klicKNalezeni) : null;
    }

    // Zjisti zda mistnost obsahuje predmet
    public boolean obsahujePredmet(String nazevPredmetu) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazevPredmetu)) {
                return true;
            }
        }
        return false;
    }

    // Vrati predmet bez jeho odstraneni
    public Predmet getPredmet(String nazevPredmetu) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazevPredmetu)) {
                return predmety.get(klic);
            }
        }
        return null;
    }

    // Vlozi postavu do mistnosti
    public void vlozPostavu(Postava postava) {
        if (postava != null) {
            postavy.put(postava.getJmeno(), postava);
        }
    }

    // Vrati postavu z mistnosti
    public Postava getPostava(String jmeno) {
        for (String klic : postavy.keySet()) {
            if (klic.equalsIgnoreCase(jmeno)) {
                return postavy.get(klic);
            }
        }
        return null;
    }
}
