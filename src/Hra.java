
public class Hra {

    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private SeznamPrikazu seznamPrikazu;

    // Vytvori hru, inicializuje mistnosti a prikazy
    public Hra() {
        herniPlan = new HerniPlan();
        seznamPrikazu = new SeznamPrikazu();

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

    public String vratUvitani() {
        return "Vitejte!\n" +
                "Pribeh ztraceneho talismanu na zapase FC BZZ.\n" +
                "Napiste 'pomoc', pokud si nevite rady.\n\n" +
                herniPlan.getAktualniMistnost().getDlouhyPopis();
    }

    public String vratEpilog() {
        return "Diky, ze jste si zahrali. ";
    }

    public boolean konecHry() {
        return konecHry;
    }

    // Zpracuje prikaz od hrace
    public String zpracujPrikaz(String radek) {
        if (radek == null || radek.trim().isEmpty()) {
            return "Neco musis napsat.";
        }

        String[] slova = radek.trim().split("\\s+");
        String slovoPrikazu = slova[0].toLowerCase();
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

    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    // Nastavi konec hry
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
}
