// Prikaz pomoc - vypise dostupne prikazy
public class PrikazPomoc implements IPrikaz {

    private SeznamPrikazu platnePrikazy;

    // Konstruktor prikazu pomoc
    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    // Provede prikaz - vypise dostupne prikazy
    @Override
    public String proved(String[] parametry) {
        return "Tvym ukolem je neco udelat...\n\n"
                + "Muzes pouzit tyto prikazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }

    // Vrati nazev prikazu
    @Override
    public String getNazev() {
        return "pomoc";
    }
}
