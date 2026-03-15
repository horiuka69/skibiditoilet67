/**
 * Prikaz pro zobrazeni obsahu hracova batohu.
 */
public class PrikazInventar implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan.
     */
    public PrikazInventar(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede vypis obsahu batohu.
     * @param parametry Prikaz neocekava zadne parametry.
     * @return Textovy seznam predmetu v batohu.
     */
    @Override
    public String proved(String[] parametry) {
        if (parametry.length > 0) {
            return "Prikaz inventar nema parametry.";
        }

        return plan.getHrac().getBatoh().getSeznamVeci();
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "inventar";
    }
}
