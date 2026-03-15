/**
 * Prikaz pro presun do jine mistnosti (pohyb hrace).
 */
public class PrikazJdi implements IPrikaz {

    private final HerniPlan plan; // Reference na herni plan

    /**
     * Konstruktor prikazu.
     * @param plan Herni plan, ve kterem se hrac pohybuje.
     */
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provede presun hrace do sousedni mistnosti.
     * @param parametry Nazev cilove mistnosti.
     * @return Zprava o uspesnem presunu nebo chybove hlaseni.
     */
    @Override
    public String proved(String[] parametry) {
        // Kontrola, zda hrac zadal kam chce jit
        if (parametry.length == 0) {
            return "Kam mam jit? Musis zadat jmeno vychodu.";
        }

        String smer = parametry[0];
        Mistnost aktualniMistnost = plan.getAktualniMistnost();
        Mistnost sousedniMistnost = aktualniMistnost.vratVychod(smer);

        // Kontrola existence vychodu
        if (sousedniMistnost == null) {
            return "Tam se odsud jit neda.";
        }

        // Kontrola zamcenych dveri do VIP loze
        if (sousedniMistnost.getNazev().equals("VIP_loze") && !plan.isVipOdemceno()) {
            return "VIP loze je zamcena. Potrebujes klic.";
        }

        // Zmena aktualni mistnosti hrace
        plan.getHrac().setAktualniMistnost(sousedniMistnost);
        return sousedniMistnost.getDlouhyPopis();
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "jdi";
    }
}
