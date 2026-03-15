import java.io.IOException;

/**
 * Trida HerniPlan udrzuje stav herniho sveta.
 * Obsahuje informace o hraci, odemcenych castech a inicializaci sveta.
 */
public class HerniPlan {

    private Hrac hrac; // Reference na hrace
    private boolean vipOdemceno = false; // Flag, zda je odemcene VIP
    private boolean kvetuseNapojena = false; // Flag, zda je Kvetuse spokojena

    /**
     * Konstruktor herniho planu.
     */
    public HerniPlan() {
        zalozProstorHry();
    }

    /**
     * Vytvori herni prostory a nacte data ze souboru JSON.
     */
    private void zalozProstorHry() {
        try {
            // Nacteni sveta z externiho souboru
            Mistnost startovniMistnost = NacitacSveta.nacistSvet("res/gamedata.json");
            if (startovniMistnost == null) {
                startovniMistnost = new Mistnost("Prazdnota", "Svet se nepodarilo nacist.");
            }
            this.hrac = new Hrac(startovniMistnost);
        } catch (IOException e) {
            // Zpracovani chyby pri nacitani
            System.out.println("Chyba pri nacitani sveta: " + e.getMessage());
            Mistnost startovniMistnost = new Mistnost("Chyba", "Chyba souboru: " + e.getMessage());
            this.hrac = new Hrac(startovniMistnost);
        }
    }

    /**
     * Vraci mistnost, ve ktere se hrac prave nachazi.
     */
    public Mistnost getAktualniMistnost() {
        return hrac.getAktualniMistnost();
    }

    /**
     * Vraci referenci na hrace.
     */
    public Hrac getHrac() {
        return hrac;
    }

    /**
     * Kontroluje, zda byly splneny vitezne podminky.
     * @return true, pokud hrac vyhral.
     */
    public boolean jeVyhra() {
        // Vyhra je pokud je hrac na hristi a ma talisman
        return hrac.getAktualniMistnost().getNazev().equalsIgnoreCase("Hriste") && hrac.getBatoh().obsahuje("talisman");
    }

    /**
     * Vraci, zda je VIP prostor odemcen.
     */
    public boolean isVipOdemceno() {
        return vipOdemceno;
    }

    /**
     * Nastavuje stav odemceni VIP prostoru.
     */
    public void setVipOdemceno(boolean vipOdemceno) {
        this.vipOdemceno = vipOdemceno;
    }

    /**
     * Vraci, zda je postava Kvetuse napojena (spokojena).
     */
    public boolean isKvetuseNapojena() {
        return kvetuseNapojena;
    }

    /**
     * Nastavuje stav napojeni Kvetuse.
     */
    public void setKvetuseNapojena(boolean kvetuseNapojena) {
        this.kvetuseNapojena = kvetuseNapojena;
    }
}
