public class PrikazPoloz implements IPrikaz {

    private final HerniPlan plan;

    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mam polozit? Musis zadat nazev predmetu.";
        }

        String nazev = parametry[0];
        Batoh batoh = plan.getHrac().getBatoh();

        if (!batoh.obsahuje(nazev)) {
            return "To nemas v batohu.";
        }

        Predmet predmet = batoh.vyber(nazev);
        plan.getAktualniMistnost().vlozPredmet(predmet);

        return "Polozil jsi " + nazev + ".";
    }

    @Override
    public String getNazev() {
        return "poloz";
    }
}
