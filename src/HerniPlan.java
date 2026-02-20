import java.io.IOException;

public class HerniPlan {

    private Hrac hrac;
    private boolean vipOdemceno = false;
    private boolean kvetuseNapojena = false;

    public HerniPlan() {
        zalozProstorHry();
    }

    private void zalozProstorHry() {
        try {
            Mistnost startovniMistnost = NacitacSveta.nacistSvet("res/gamedata.json");
            if (startovniMistnost == null) {
                startovniMistnost = new Mistnost("Prazdnota", "Svet se nepodarilo nacist.");
            }
            this.hrac = new Hrac(startovniMistnost);
        } catch (IOException e) {
            System.out.println("Chyba pri nacitani sveta: " + e.getMessage());
            Mistnost startovniMistnost = new Mistnost("Chyba", "Chyba souboru: " + e.getMessage());
            this.hrac = new Hrac(startovniMistnost);
        }
    }

    public Mistnost getAktualniMistnost() {
        return hrac.getAktualniMistnost();
    }

    public Hrac getHrac() {
        return hrac;
    }

    public boolean jeVyhra() {
        // Vyhra je pokud je hrac na hristi a ma talisman (ignorujeme case pro nazev
        // mistnosti pro jistotu)
        return hrac.getAktualniMistnost().getNazev().equalsIgnoreCase("Hriste") && hrac.getBatoh().obsahuje("talisman");
    }

    public boolean isVipOdemceno() {
        return vipOdemceno;
    }

    public void setVipOdemceno(boolean vipOdemceno) {
        this.vipOdemceno = vipOdemceno;
    }

    public boolean isKvetuseNapojena() {
        return kvetuseNapojena;
    }

    public void setKvetuseNapojena(boolean kvetuseNapojena) {
        this.kvetuseNapojena = kvetuseNapojena;
    }
}
