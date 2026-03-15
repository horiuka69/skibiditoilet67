
/**
 * Trida Hra predstavuje logiku cele hry.
 * Stara se o inicializaci, zpracovani prikazu a kontrolu konce hry.
 */
public class Hra {

    private HerniPlan herniPlan; // Reference na herni plan (svet hry)
    private boolean konecHry = false; // Flag urcujici, zda hra jiz skoncila
    private SeznamPrikazu seznamPrikazu; // Seznam vsech platnych prikazu

    /**
     * Konstruktor, ktery vytvori hru a inicializuje mistnosti a prikazy.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        seznamPrikazu = new SeznamPrikazu();

        // Registrace vsech prikazu, ktere lze ve hre pouzit
        seznamPrikazu.vlozPrikaz(new PrikazPomoc(seznamPrikazu));
        seznamPrikazu.vlozPrikaz(new PrikazNapoveda(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazKonec(this));

        seznamPrikazu.vlozPrikaz(new PrikazJdi(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazVezmi(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazPoloz(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazMluv(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazInventar(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazPouzij(herniPlan));
        seznamPrikazu.vlozPrikaz(new PrikazDej(herniPlan));
    }

    /**
     * Vraci uvitaci text pro hrace na zacatku hry.
     */
    public String vratUvitani() {
        return "Vitejte!\n" +
                "Pribeh ztraceneho talismanu na zapase FC BZZ.\n" +
                "Napiste 'pomoc', pokud si nevite rady.\n\n" +
                herniPlan.getAktualniMistnost().getDlouhyPopis();
    }

    /**
     * Vraci text, ktery se vypise pri ukonceni hry.
     */
    public String vratEpilog() {
        return "Diky, ze jste si zahrali. ";
    }

    /**
     * Informuje o tom, zda hra skoncila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda, ktera zpracuje vlozeny radek textu jako prikaz.
     * 
     * @param radek Radek textu zadany hracem.
     * @return Odpoved hry na zadany prikaz.
     */

    public String zpracujPrikaz(String radek) {
        // Kontrola prazdneho vstupu
        if (radek == null || radek.trim().isEmpty()) {
            return "Neco musis napsat.";
        }

        // Rozdeleni vstupu na slova
        String[] slova = radek.trim().split("\\s+");
        String slovoPrikazu = slova[0].toLowerCase();
        String[] parametry = new String[slova.length - 1];

        // Naplneni parametru prikazu
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }

        String textKVypsani;
        // Overeni, zda je zadany prikaz platny
        if (seznamPrikazu.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = seznamPrikazu.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        } else {
            textKVypsani = "Nerozumim, co po mne chces. Zkus napsat 'pomoc'.";
        }

        // Kontrola vitezne nebo proherni podminky v konkretni mistnosti
        if (herniPlan.getAktualniMistnost().getNazev().equalsIgnoreCase("Hriste")) {
            konecHry = true;
            if (herniPlan.jeVyhra()) {
                textKVypsani += "\n\nGRATULUJU! Mas talisman a muzes hrat finale. Vyhral jsi!";
            } else {
                textKVypsani += "\n\nDostal ses na hriste, ale nemas talisman. FC BZZ prohraje a tvoje kariera konci. Prohral jsi.";
            }
        }

        return textKVypsani;
    }

    /**
     * Vraci herni plan (svet hry).
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    /**
     * Umoznuje manualne nastavit konec hry.
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
}
