// Prikaz pro presun do jine mistnosti.
public class PrikazJdi implements IPrikaz {

    private final HerniPlan plan;

    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Kam mam jit? Musis zadat jmeno vychodu.";
        }

        String smer = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Mistnost sousedniMistnost = aktualniMistnost.vratVychod(smer);

        if (sousedniMistnost == null) {
            return "Tam se odsud jit neda.";
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
