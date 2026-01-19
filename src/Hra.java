
public class Hra {

    private HerniPlan herniPlan;
    private boolean konecHry = false;

    public Hra() {
        herniPlan = new HerniPlan();
    }

    public String vratUvitani() {
        return "Vítejte!\n" +
                "Toto je nová adventura.\n" +
                "Napište 'pomoc', pokud si nevíte rady.\n\n" +
                herniPlan.getAktualniMistnost().getDlouhyPopis();
    }

    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }

    public boolean konecHry() {
        return konecHry;
    }

    public String zpracujPrikaz(String radek) {
        // TODO: Implementovat zpracování příkazů (delegace na SeznamPrikazu)
        return "Zatím neumím zpracovat příkazy.";
    }

    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    public void setKonecHry(boolean konecHry) {
    }
}
