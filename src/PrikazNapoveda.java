// Prikaz napoveda - poskytne radu k aktualni situaci
public class PrikazNapoveda implements IPrikaz {

    private final HerniPlan plan;

    public PrikazNapoveda(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        String mistnost = plan.getAktualniMistnost().getNazev();

        if (mistnost.equalsIgnoreCase("Satna"))
            return "Promluv si s trenerem, videl pry u skladu neco podezreleho.";
        if (mistnost.equalsIgnoreCase("Chodba"))
            return "Zkus mluvit se spoluhracem Mlzanem, vedel neco o Hubertovi.";
        if (mistnost.equalsIgnoreCase("Bufet"))
            return "Kvetuse ti blokuje pristup k informacim. Musis ji uplatit pitim z kancelare.";
        if (mistnost.equalsIgnoreCase("Sklad"))
            return "Hledej klic v hromade micu. Trener rikal, ze se tu nekdo motal.";
        if (mistnost.equalsIgnoreCase("VIP_loze"))
            return "Vezmi si talisman a utikej na hriste!";

        return "Prozkoumavej mistnosti, hledej predmety a mluv s postavami.";
    }

    @Override
    public String getNazev() {
        return "napoveda";
    }
}
