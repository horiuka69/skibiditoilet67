/**
 * Trida Predmet reprezentuje predmety, ktere se nachazi v hernim svete.
 * Predmet ma nazev, popis a vlastnost, zda ho lze sebrat do batohu.
 */
public class Predmet {

    private String nazev; // Nazev predmetu
    private String popis; // Popis predmetu
    private boolean prenositelny; // Flag, zda hrac muze predmet vzit do batohu

    /**
     * Konstruktor pro vytvoreni predmetu.
     * @param nazev Nazev predmetu.
     * @param popis Strucny popis predmetu.
     * @param prenositelny Urcuje, zda je predmet mozne sebrat.
     */
    public Predmet(String nazev, String popis, boolean prenositelny) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
    }

    /**
     * Vraci nazev predmetu.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vraci popis predmetu.
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Zjisti, zda je predmet prenositelny.
     */
    public boolean jePrenositelny() {
        return prenositelny;
    }
}
