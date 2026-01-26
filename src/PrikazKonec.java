// Prikaz konec - ukonci hru
public class PrikazKonec implements IPrikaz {

    private Hra hra;

    // Konstruktor prikazu konec
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    // Provede prikaz - ukonci hru
    @Override
    public String proved(String[] parametry) {
        if (parametry.length > 0) {
            return "Konec neocekava zadny parametr. Chces ukoncit hru? Napis jen: konec";
        }
        hra.setKonecHry(true);
        return "Diky za hru. Nashledanou!";
    }

    // Vrati nazev prikazu
    @Override
    public String getNazev() {
        return "konec";
    }
}
