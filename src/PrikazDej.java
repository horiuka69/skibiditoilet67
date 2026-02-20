public class PrikazDej implements IPrikaz {

    private final HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length < 2) {
            return "Komu mam co dat? Zadej: dej [predmet] [postava]";
        }

        String nazevPredmetu = parametry[0];
        String jmenoPostavy = parametry[1];

        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Batoh batoh = plan.getHrac().getBatoh();

        if (!batoh.obsahuje(nazevPredmetu)) {
            return "Predmet '" + nazevPredmetu + "' nemas v batohu.";
        }

        Postava postava = aktualniMistnost.getPostava(jmenoPostavy);
        if (postava == null) {
            return "Postava '" + jmenoPostavy + "' tu neni.";
        }

        if (nazevPredmetu.equalsIgnoreCase("piti") && jmenoPostavy.equalsIgnoreCase("Kvetuse")) {
            batoh.vyber("piti");
            plan.setKvetuseNapojena(true);
            return "Dal jsi fanynce Kvetusi piti. Uklidnila se a rekla ti: 'Videla jsem Huberta, jak bezi k VIP lozi s necim pod dresem!'";
        }

        return postava.getJmeno() + " od tebe " + nazevPredmetu + " nechce.";
    }

    @Override
    public String getNazev() {
        return "dej";
    }
}
