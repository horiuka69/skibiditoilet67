/**
 * Prikaz pro podrobne prozkoumani predmetu, postavy nebo casti mistnosti.
 */
public class PrikazProzkoumej implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede prozkoumani zadaneho objektu.
     * @param parametry Nazev objektu k prozkoumani.
     * @return Popis objektu nebo zprava o nalezu.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola, zda hrac neco zadal
        if (parametry.length == 0) {
            return "Co mam prozkoumat? Musis zadat nazev.";
        }

        String nazev = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Batoh batoh = plan.getHrac().getBatoh();

        // Specialni logika pro nalezeni klice ve Skladu pod micemi
        if (nazev.equalsIgnoreCase("mice") && aktualniMistnost.getNazev().equalsIgnoreCase("Sklad")) {
            if (!aktualniMistnost.obsahujePredmet("klic") && !batoh.obsahuje("klic")) {
                Predmet klic = new Predmet("klic", "Tezky klic s priveskem loga klubu. Pasuje do VIP loze.", true);
                aktualniMistnost.vlozPredmet(klic);
                return "Prozkoumal jsi hromadu micu a nasel jsi klic!";
            }
        }

        // Prohledame predmety v mistnosti
        if (aktualniMistnost.obsahujePredmet(nazev)) {
            return aktualniMistnost.getPredmet(nazev).getPopis();
        }

        // Prohledame predmety v batohu hrace
        if (batoh.obsahuje(nazev)) {
            return batoh.getPredmet(nazev).getPopis();
        }

        // Prohledame postavy v mistnosti
        Postava postava = aktualniMistnost.getPostava(nazev);
        if (postava != null) {
            return postava.getPopis();
        }

        return "Nic takoveho tu nevidim.";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "prozkoumej";
    }
}
