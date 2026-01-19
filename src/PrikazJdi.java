//Příkaz pro přesun do jiné místnosti.
public class PrikazJdi implements IPrikaz {

    private final HerniPlan plan;

    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu.";
        }

        String smer = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Mistnost sousedniMistnost = aktualniMistnost.vratVychod(smer);

        if (sousedniMistnost == null) {
            return "Tam se odsud jít nedá.";
        } else {
            plan.getHrac().setAktualniMistnost(sousedniMistnost);
            return sousedniMistnost.getDlouhyPopis();
        }
    }

    @Override
    public String getNazev() {
        return "jdi";
    }
}
