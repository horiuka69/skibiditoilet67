/**
 * Prikaz konec slouzi k predcasnemu ukonceni hry hracem.
 */
public class PrikazKonec implements IPrikaz {

    private Hra hra; // Reference na objekt hry

    /**
     * Konstruktor prikazu.
     * @param hra Instance hry, ktera ma byt ukoncena.
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * Provede ukonceni hry.
     * @param parametry Prikaz neocekava parametry.
     * @return Loucici zprava.
     */
    @Override
    public String proved(String[] parametry) {
        if (parametry.length > 0) {
            return "Konec neocekava zadny parametr. Chces ukoncit hru? Napis jen: konec";
        }
        // Nastaveni flagu pro ukonceni hlavni smycky
        hra.setKonecHry(true);
        return "Diky za hru. Nashledanou!";
    }

    /**
     * Vraci nazev prikazu.
     */
    @Override
    public String getNazev() {
        return "konec";
    }
}
