//Příkaz pro zobrazení herní nápovědy (co dál dělat).

public class PrikazNapoveda implements IPrikaz {

    public PrikazNapoveda(HerniPlan plan) {
    }

    @Override
    public String proved(String[] parametry) {
        return "";
    }

    @Override
    public String getNazev() {
        return "napoveda";
    }
}
