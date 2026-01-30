public class PrikazPouzij implements IPrikaz {

    public PrikazPouzij(HerniPlan plan) {
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mam pouzit?";
        }
        return "Bohuzel, nevim jak to pouzit.";
    }

    @Override
    public String getNazev() {
        return "pouzij";
    }
}
