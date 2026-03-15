/**
 * Prikaz pro pouziti predmetu z batohu.
 * Obsahuje specialni logiku pro interakci s prostredim (odemceni dveri, nakrmeni/napojeni NPC).
 */
public class PrikazPouzij implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede pouziti predmetu.
     * @param parametry Nazev predmetu k pouziti.
     * @return Zprava o vysledku pouziti.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola parametru
        if (parametry.length == 0) {
            return "Co mam pouzit?";
        }

        String nazev = parametry[0];
        Batoh batoh = plan.getHrac().getBatoh();

        // Kontrola, zda hrac predmet ma
        if (!batoh.obsahuje(nazev)) {
            return "Predmet '" + nazev + "' nemas v batohu.";
        }

        String mistnost = plan.getAktualniMistnost().getNazev();

        // Logika pro klic
        if (nazev.equalsIgnoreCase("klic")) {
            if (mistnost.equalsIgnoreCase("Bufet")) {
                plan.setVipOdemceno(true); // Odemceni VIP loze
                return "Odemkl jsi VIP lozi.";
            } else {
                return "Tady ten klic neni k cemu.";
            }
        }

        // Logika pro piti
        if (nazev.equalsIgnoreCase("piti")) {
            if (mistnost.equalsIgnoreCase("Bufet")) {
                batoh.vyber("piti"); // Spotrebovani predmetu
                plan.setKvetuseNapojena(true); // Zmena stavu sveta
                return "Dal jsi fanynce Kvetusi piti. Uklidnila se a rekla ti: 'Videl jsem Huberta, jak bezi k VIP lozi s necim pod dresem!'";
            } else {
                return "Tady neni nikdo, komu bys piti dal.";
            }
        }

        return "Bohuzel, nevim jak '" + nazev + "' pouzit.";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "pouzij";
    }
}
