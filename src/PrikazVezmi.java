public class PrikazVezmi implements IPrikaz {

    private final HerniPlan plan;

    public PrikazVezmi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mam vzit? Musis zadat nazev predmetu.";
        }

        String nazev = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();

        if (!aktualniMistnost.obsahujePredmet(nazev)) {
            return "To tu neni.";
        }

        Predmet predmet = aktualniMistnost.getPredmet(nazev);
        if (!predmet.jePrenositelny()) {
            return "To neunese.";
        }

        Batoh batoh = plan.getHrac().getBatoh();
        if (batoh.jePlny()) {
            return "Batoh je plny.";
        }

        // Presun predmetu
        aktualniMistnost.vezmiPredmet(nazev);
        batoh.vloz(predmet);

        return "Vzal jsi " + nazev + ".";
    }

    @Override
    public String getNazev() {
        return "vezmi";
    }
}
