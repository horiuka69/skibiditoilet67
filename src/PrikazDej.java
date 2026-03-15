/**
 * Prikaz pro darovani predmetu postave.
 */
public class PrikazDej implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede predani predmetu postave.
     * @param parametry [0] - nazev predmetu, [1] - jmeno postavy.
     * @return Zprava o vysledku predani.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola poctu parametru
        if (parametry.length < 2) {
            return "Komu mam co dat? Zadej: dej [predmet] [postava]";
        }

        String nazevPredmetu = parametry[0];
        String jmenoPostavy = parametry[1];

        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Batoh batoh = plan.getHrac().getBatoh();

        // Kontrola, zda hrac predmet ma
        if (!batoh.obsahuje(nazevPredmetu)) {
            return "Predmet '" + nazevPredmetu + "' nemas v batohu.";
        }

        // Kontrola, zda je postava v mistnosti
        Postava postava = aktualniMistnost.getPostava(jmenoPostavy);
        if (postava == null) {
            return "Postava '" + jmenoPostavy + "' tu neni.";
        }

        // Specialni logika pro fanynku Kvetusi
        if (nazevPredmetu.equalsIgnoreCase("piti") && jmenoPostavy.equalsIgnoreCase("Kvetuse")) {
            batoh.vyber("piti");
            plan.setKvetuseNapojena(true);
            return "Dal jsi fanynce Kvetusi piti. Uklidnila se a rekla ti: 'Videla jsem Huberta, jak bezi k VIP lozi s necim pod dresem!'";
        }

        return postava.getJmeno() + " od tebe " + nazevPredmetu + " nechce.";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "dej";
    }
}
