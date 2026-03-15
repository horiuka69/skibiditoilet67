/**
 * Trida reprezentujici hrace v adventurze.
 * Hrac ma u sebe batoh a vi, ve ktere mistnosti se nachazi.
 */
public class Hrac {

    private Mistnost aktualniMistnost; // Mistnost, ve ktere se hrac prave nachazi
    private Batoh batoh; // Inventar hrace

    /**
     * Konstruktor hrace, ktery ho umisti do pocatecni mistnosti a vytvori mu batoh.
     */
    public Hrac(Mistnost pocatecniMistnost) {
        this.aktualniMistnost = pocatecniMistnost;
        this.batoh = new Batoh();
    }

    /**
     * Vraci mistnost, ve ktere se hrac nachazi.
     */
    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    /**
     * Nastavuje aktualni mistnost pro hrace (pohyb mezi mistnostmi).
     */
    public void setAktualniMistnost(Mistnost mistnost) {
        this.aktualniMistnost = mistnost;
    }

    /**
     * Vraci batoh hrace pro praci s predmety.
     */
    public Batoh getBatoh() {
        return batoh;
    }
}
