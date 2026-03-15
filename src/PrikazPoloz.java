/**
 * Prikaz pro vylozeni predmetu z batohu do mistnosti.
 */
public class PrikazPoloz implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede polozeni predmetu v aktualni mistnosti.
     * @param parametry Nazev predmetu k polozeni.
     * @return Zprava o vysledku akce.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola parametru
        if (parametry.length == 0) {
            return "Co mam polozit? Musis zadat nazev predmetu.";
        }

        String nazev = parametry[0];
        Batoh batoh = plan.getHrac().getBatoh();

        // Kontrola, zda predmet v batohu vubec je
        if (!batoh.obsahuje(nazev)) {
            return "To nemas v batohu.";
        }

        // Vyjmuti z batohu a vlozeni do mistnosti
        Predmet predmet = batoh.vyber(nazev);
        plan.getAktualniMistnost().vlozPredmet(predmet);

        return "Polozil jsi " + nazev + ".";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "poloz";
    }
}
