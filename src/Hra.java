
public class Hra {

    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private SeznamPrikazu seznamPrikazu;

    // Vytvori hru, inicializuje mistnosti a prikazy
    public Hra() {
        herniPlan = new HerniPlan();
        seznamPrikazu = new SeznamPrikazu();

        // Registrace vsech prikazu
        seznamPrikazu.vlozPrikaz(new PrikazPomoc(seznamPrikazu));
        seznamPrikazu.vlozPrikaz(new PrikazNapoveda(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazKonec(this));

        // TODO: Zde pridat dalsi prikazy (jdi, vezmi, poloz, atd.)
    }

    // Vrati uvitaci text hry
    public String vratUvitani() {
        return "Vitejte!\n" +
                "Toto je nova adventura.\n" +
                "Napiste 'pomoc', pokud si nevite rady.\n\n" +
                herniPlan.getAktualniMistnost().getDlouhyPopis();
    }

    // Vrati text pri ukonceni hry
    public String vratEpilog() {
        return "Dik, ze jste si zahrali.  Ahoj.";
    }

    // Zjisti zda hra skoncila
    public boolean konecHry() {
        return konecHry;
    }

    // Zpracuje prikaz od hrace
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split(" ");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];

        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }

        String textKVypsani;
        if (seznamPrikazu.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = seznamPrikazu.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        } else {
            textKVypsani = "Nerozumim, co po mne chces. Zkus napsat 'pomoc'.";
        }

        return textKVypsani;
    }

    // Vrati herni plan
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    // Nastavi konec hry
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
}
