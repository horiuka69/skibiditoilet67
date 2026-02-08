// Prikaz napoveda - poskytne radu k aktualní situaci
public class PrikazNapoveda implements IPrikaz {

    private final HerniPlan plan;

    public PrikazNapoveda(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        String mistnost = plan.getAktualniMistnost().getNazev();

        if (mistnost.equals("Satna"))
            return "Promluv si s trenerem, mozna neco vi.";
        if (mistnost.equals("Chodba"))
            return "Zkus mluvit se spoluhracem Mlzanem.";
        if (mistnost.equals("Bufet"))
            return "Kvetuse vypada smutne, mozna neco potrebuje k piti.";
        if (mistnost.equals("Sklad"))
            return "Prozkoumej mice, nekde tam musi byt klic!";
        if (mistnost.equals("VIP_loze"))
            return "Vezmi si talisman a utikej na hriste!";

        return "Prozkoumávej mistnosti, hledej predmety a mluv s postavami.";
    }

    @Override
    public String getNazev() {
        return "napoveda";
    }
}
