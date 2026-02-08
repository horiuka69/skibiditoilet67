// Prikaz pomoc - vypise dostupne prikazy a cil hry
public class PrikazPomoc implements IPrikaz {

    private SeznamPrikazu platnePrikazy;

    // Konstruktor prikazu pomoc
    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    // Provede prikaz - vypise dostupne prikazy a cil
    @Override
    public String proved(String[] parametry) {
        return "Tvym ukolem je najit rodinny talisman (Zlatou kopacku) a dostat se na hriste.\n"
                + "Bez nej finale nevyhrajes. Zkus mluvit s lidmi v satne a na chodbe.\n\n"
                + "Muzes pouzit tyto prikazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }

    // Vrati nazev prikazu
    @Override
    public String getNazev() {
        return "pomoc";
    }
}
