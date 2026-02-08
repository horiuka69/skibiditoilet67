import java.io.IOException;

public class HerniPlan {

    private Hrac hrac;
    private boolean vipOdemceno = false;

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
        return hrac.getAktualniMistnost().getNazev().equals("Hriste") && hrac.getBatoh().obsahuje("talisman");
    }

    public boolean isVipOdemceno() {
        return vipOdemceno;
    }

    public void setVipOdemceno(boolean vipOdemceno) {
        this.vipOdemceno = vipOdemceno;
    }
}
