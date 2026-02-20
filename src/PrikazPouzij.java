public class PrikazPouzij implements IPrikaz {

    private final HerniPlan plan;

    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String proved(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mam pouzit?";
        }

        String nazev = parametry[0];
        Batoh batoh = plan.getHrac().getBatoh();

        if (!batoh.obsahuje(nazev)) {
            return "Predmet '" + nazev + "' nemas v batohu.";
        }

        String mistnost = plan.getAktualniMistnost().getNazev();

        if (nazev.equalsIgnoreCase("klic")) {
            if (mistnost.equalsIgnoreCase("Bufet")) {
                plan.setVipOdemceno(true);
                return "Odemkl jsi VIP lozi.";
            } else {
                return "Tady ten klic neni k cemu.";
            }
        }

        if (nazev.equalsIgnoreCase("piti")) {
            if (mistnost.equalsIgnoreCase("Bufet")) {
                batoh.vyber("piti");
                plan.setKvetuseNapojena(true);
                return "Dal jsi fanynce Kvetusi piti. Uklidnila se a rekla ti: 'Videl jsem Huberta, jak bezi k VIP lozi s necim pod dresem!'";
            } else {
                return "Tady neni nikdo, komu bys piti dal.";
            }
        }

        return "Bohuzel, nevim jak '" + nazev + "' pouzit.";
    }

    @Override
    public String getNazev() {
        return "pouzij";
    }
}
