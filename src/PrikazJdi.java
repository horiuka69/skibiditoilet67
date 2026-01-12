//Příkaz pro přesun do jiné místnosti.

public class PrikazJdi implements IPrikaz {

    public PrikazJdi(HerniPlan plan) {
    }

    @Override
    public String proved(String[] parametry) {
        return "";
    }

    @Override
    public String getNazev() {
        return "jdi";
    }
}
