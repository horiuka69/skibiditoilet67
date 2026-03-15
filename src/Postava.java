import java.util.ArrayList;
import java.util.List;

/**
 * NPC postava ve hre (non-player character).
 * Postava ma jmeno, popis a seznam replik, ktere muze rikat.
 */
public class Postava {

    private String jmeno; // Jmeno postavy
    private String popis; // Popis postavy (vzhled, atd.)
    private List<String> repliky; // Seznam dialogu postavy
    private int aktualniReplika; // Index aktualni repliky pro mluveni

    /**
     * Konstruktor, vytvori postavu se jmenem a popisem.
     */
    public Postava(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.repliky = new ArrayList<>();
        this.aktualniReplika = 0;
    }

    /**
     * Prida novou repliku do seznamu dialogu postavy.
     */
    public void pridejRepliku(String replika) {
        repliky.add(replika);
    }

    /**
     * Vraci jmeno postavy.
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vraci popis postavy.
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Postava promluvi. Vrati aktualni repliku a posune se na dalsi.
     * @return Text, ktery postava rika.
     */
    public String mluv() {
        if (repliky.isEmpty()) {
            return jmeno + ": Nemam co rici.";
        }

        String odpoved = jmeno + ": " + repliky.get(aktualniReplika);
        // Cyklicke stridani replik
        aktualniReplika = (aktualniReplika + 1) % repliky.size();
        return odpoved;
    }
}
