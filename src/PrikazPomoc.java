/**
 * Prikaz pomoc vypise strucny cil hry a seznam vsech dostupnych prikazu.
 */
public class PrikazPomoc implements IPrikaz {

    private SeznamPrikazu platnePrikazy; // Reference na seznam vsech prikazu

    /**
     * Konstruktor prikazu.
     * @param platnePrikazy Seznam prikazu, ktere maji byt vypsany.
     */
    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Provede vypsani napovedy.
     * @param parametry Prikaz neocekava parametry.
     * @return Text s napovedou a dostupnymi prikazy.
     */
    @Override
    public String proved(String[] parametry) {
        return "Tvym ukolem je najit rodinny talisman (Zlatou kopacku) a dostat se na hriste.\n"
                + "Bez nej finale nevyhrajes. Zkus mluvit s lidmi v satne a na chodbe.\n\n"
                + "Muzes pouzit tyto prikazy:\n"
                + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "pomoc";
    }
}
