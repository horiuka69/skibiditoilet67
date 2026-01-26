//Prikaz napoveda - zobrazi herni napovedu

public class PrikazNapoveda implements IPrikaz {

    // Konstruktor prikazu napoveda
    public PrikazNapoveda(HerniPlan plan) {
        // Plan neni aktualne potreba, ale muze byt v budoucnu pouzit
    }

    // Provede prikaz - zobrazi napovedu
    @Override
    public String proved(String[] parametry) {
        return "Jsi ve hre. Zkus prozkoumavat mistnosti, " +
                "mluvit s postavami a hledat predmety.\n" +
                "Nezapomen se podivat do batohu a pouzivat sve veci!";
    }

    // Vrati nazev prikazu
    @Override
    public String getNazev() {
        return "napoveda";
    }
}
