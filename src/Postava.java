import java.util.ArrayList;
import java.util.List;

// NPC postava ve hre
public class Postava {

    private String jmeno;
    private String popis;
    private List<String> repliky;
    private int aktualniReplika;

    // Vytvori postavu s dialogem
    public Postava(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.repliky = new ArrayList<>();
        this.aktualniReplika = 0;
    }

    // Prida repliku do seznamu
    public void pridejRepliku(String replika) {
        repliky.add(replika);
    }

    // Vrati jmeno postavy
    public String getJmeno() {
        return jmeno;
    }

    // Vrati popis postavy
    public String getPopis() {
        return popis;
    }

    // Postava promluvi - vrati dalsi repliku
    public String mluv() {
        if (repliky.isEmpty()) {
            return jmeno + ": Nemam co rici.";
        }

        String odpoved = jmeno + ": " + repliky.get(aktualniReplika);
        aktualniReplika = (aktualniReplika + 1) % repliky.size();
        return odpoved;
    }
}
