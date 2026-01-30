public class PrikazMluv implements IPrikaz {

    private final HerniPlan plan;

    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "S kym mam mluvit? Musis zadat jmeno postavy.";
        }

        String jmeno = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Postava postava = aktualniMistnost.getPostava(jmeno);

        if (postava == null) {
            return "Ten tu neni.";
        }

        return postava.mluv();
    }

    @Override
    public String getNazev() {
        return "mluv";
    }
}
