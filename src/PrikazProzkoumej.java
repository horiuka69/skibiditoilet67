public class PrikazProzkoumej implements IPrikaz {

    private final HerniPlan plan;

    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mam prozkoumat? Musis zadat nazev.";
        }

        String nazev = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Batoh batoh = plan.getHrac().getBatoh();

        // Prohledame mistnost - predmety
        if (aktualniMistnost.obsahujePredmet(nazev)) {
            return aktualniMistnost.getPredmet(nazev).getPopis();
        }

        // Prohledame batoh
        if (batoh.obsahuje(nazev)) {
            return batoh.getPredmet(nazev).getPopis();
        }

        // Prohledame mistnost - postavy
        Postava postava = aktualniMistnost.getPostava(nazev);
        if (postava != null) {
            return postava.getPopis();
        }

        return "Nic takoveho tu nevidim.";
    }

    @Override
    public String getNazev() {
        return "prozkoumej";
    }
}
