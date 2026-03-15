import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Trida Mistnost reprezentuje jednotlivou lokaci ve hre.
 * Mistnost v sobe udrzuje mozne vychody, predmety a postavy, ktere se v ni nachazi.
 */
public class Mistnost {

    private String nazev; // Nazev mistnosti
    private String popis; // Popis mistnosti
    private Map<String, Mistnost> vychody; // Sousedni mistnosti
    private Map<String, Predmet> predmety; // Predmety v mistnosti
    private Map<String, Postava> postavy; // Postavy v mistnosti

    /**
     * Konstruktor, vytvori mistnost se zadanym nazvem a popisem.
     */
    public Mistnost(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashMap<>();
        this.predmety = new HashMap<>();
        this.postavy = new HashMap<>();
    }

    /**
     * Definuje vychod z teto mistnosti do jine.
     */
    public void setVychod(Mistnost vedlejsi) {
        if (vedlejsi != null) {
            vychody.put(vedlejsi.getNazev(), vedlejsi);
        }
    }

    /**
     * Vraci nazev mistnosti.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vraci podrobny popis mistnosti vcetne seznamu vychodu, predmetu a postav.
     */
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

    /**
     * Vraci mistnost, ktera sousedi s touto mistnosti v danem smeru.
     * @param nazevSouseda Nazev cilove mistnosti.
     * @return Objekt Mistnost nebo null, pokud tam neni vychod.
     */
    public Mistnost vratVychod(String nazevSouseda) {
        for (String klic : vychody.keySet()) {
            if (klic.equalsIgnoreCase(nazevSouseda)) {
                return vychody.get(klic);
            }
        }
        return null;
    }

    /**
     * Vraci kolekci vsech dostupnych vychodu z teto mistnosti.
     */
    public Collection<Mistnost> getVychody() {
        return Collections.unmodifiableCollection(vychody.values());
    }

    /**
     * Vlozi predmet do mistnosti.
     */
    public void vlozPredmet(Predmet predmet) {
        if (predmet != null) {
            predmety.put(predmet.getNazev(), predmet);
        }
    }

    /**
     * Odstrani a vrati predmet z mistnosti podle nazvu.
     */
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

    /**
     * Zjisti, zda se v mistnosti nachazi konkretni predmet.
     */
    public boolean obsahujePredmet(String nazevPredmetu) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazevPredmetu)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vraci referenci na predmet v mistnosti bez jeho odstraneni.
     */
    public Predmet getPredmet(String nazevPredmetu) {
        for (String klic : predmety.keySet()) {
            if (klic.equalsIgnoreCase(nazevPredmetu)) {
                return predmety.get(klic);
            }
        }
        return null;
    }

    /**
     * Vlozi postavu do mistnosti.
     */
    public void vlozPostavu(Postava postava) {
        if (postava != null) {
            postavy.put(postava.getJmeno(), postava);
        }
    }

    /**
     * Vyhleda a vrati postavu v mistnosti podle jmena.
     */
    public Postava getPostava(String jmeno) {
        for (String klic : postavy.keySet()) {
            if (klic.equalsIgnoreCase(jmeno)) {
                return postavy.get(klic);
            }
        }
        return null;
    }
}
