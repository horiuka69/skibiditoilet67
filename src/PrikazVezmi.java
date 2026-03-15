/**
 * Prikaz pro sebrani predmetu z mistnosti do batohu.
 */
public class PrikazVezmi implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazVezmi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede sebrani predmetu z aktualni mistnosti.
     * @param parametry Nazev predmetu.
     * @return Zprava o vysledku akce.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola parametru
        if (parametry.length == 0) {
            return "Co mam vzit? Musis zadat nazev predmetu.";
        }

        String nazev = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();

        // Kontrola, zda predmet v mistnosti je
        if (!aktualniMistnost.obsahujePredmet(nazev)) {
            return "To tu neni.";
        }

        Predmet predmet = aktualniMistnost.getPredmet(nazev);
        // Kontrola prenositelnosti
        if (!predmet.jePrenositelny()) {
            return "To neuneses.";
        }

        Batoh batoh = plan.getHrac().getBatoh();
        // Kontrola kapacity batohu
        if (batoh.jePlny()) {
            return "Batoh je plny.";
        }

        // Presun predmetu z mistnosti do batohu
        aktualniMistnost.vezmiPredmet(nazev);
        batoh.vloz(predmet);

        return "Vzal jsi " + nazev + ".";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "vezmi";
    }
}
