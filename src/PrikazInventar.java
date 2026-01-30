public class PrikazInventar implements IPrikaz {

    private final HerniPlan plan;

    public PrikazInventar(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length > 0) {
            return "Prikaz inventar nema parametry.";
        }

        return plan.getHrac().getBatoh().getSeznamVeci();
    }

    @Override
    public String getNazev() {
        return "inventar";
    }
}
