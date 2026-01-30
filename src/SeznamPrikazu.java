import java.util.HashMap;
import java.util.Map;

public class SeznamPrikazu {

    private Map<String, IPrikaz> prikazy;

    public SeznamPrikazu() {
        prikazy = new HashMap<>();
    }

    public void vlozPrikaz(IPrikaz prikaz) {
        prikazy.put(prikaz.getNazev(), prikaz);
    }

    public IPrikaz vratPrikaz(String retezec) {
        if (prikazy.containsKey(retezec)) {
            return prikazy.get(retezec);
        }
        return null;
    }

    public boolean jePlatnyPrikaz(String retezec) {
        return prikazy.containsKey(retezec);
    }

    public String vratNazvyPrikazu() {
        return String.join(" ", prikazy.keySet());
    }
}
