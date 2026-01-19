import java.io.IOException;

public class HerniPlan {

    private Hrac hrac;

    public HerniPlan() {
        zalozProstorHry();
    }

    private void zalozProstorHry() {
        try {
            // Pokusime se nacist svet z JSON souboru
            Mistnost startovniMistnost = NacitacSveta.nacistSvet("res/svet.json");

            if (startovniMistnost == null) {
                // Fallback - vytvorime jednoduchou mistnost
                startovniMistnost = new Mistnost("Prazdnota", "Svet se nepodarilo nacist.");
            }

            this.hrac = new Hrac(startovniMistnost);

        } catch (IOException e) {
            System.out.println("Chyba pri nacitani sveta: " + e.getMessage());
            // Fallback
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
        return false;
    }
}
