/**
 * Prikaz pro mluveni s postavou v mistnosti.
 */
public class PrikazMluv implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede mluveni s postavou.
     * @param parametry Jmeno postavy, se kterou chce hrac mluvit.
     * @return Replika postavy nebo chybova zprava.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola, zda hrac zadal jmeno
        if (parametry.length == 0) {
            return "S kym mam mluvit? Musis zadat jmeno postavy.";
        }

        String jmeno = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Postava postava = aktualniMistnost.getPostava(jmeno);

        // Kontrola, zda postava v mistnosti skutecne je
        if (postava == null) {
            return "Ten tu neni.";
        }

        // Ziskani repliky od postavy
        return postava.mluv();
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "mluv";
    }
}
