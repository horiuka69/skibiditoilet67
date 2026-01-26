// Predmet ve hre
public class Predmet {

    private String nazev;
    private String popis;
    private boolean prenositelny;

    // Konstruktor predmetu
    public Predmet(String nazev, String popis, boolean prenositelny) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
    }

    // Vrati nazev predmetu
    public String getNazev() {
        return nazev;
    }

    // Vrati popis predmetu
    public String getPopis() {
        return popis;
    }

    // Zjisti zda je predmet prenositelny
    public boolean jePrenositelny() {
        return prenositelny;
    }
}
