import java.util.Collection;
import java.util.Collections;
//Třída představující místnost (lokaci) ve hře.

public class Mistnost {

    public Mistnost(String nazev, String popis) {
    }

    public void setVychod(Mistnost vedlejsi) {
    }

    public String getNazev() {
        return "";
    }

    public String getDlouhyPopis() {
        return "";
    }

    public Mistnost vratVychod(String nazevSouseda) {
        return null;
    }

    public Collection<Mistnost> getVychody() {
        return Collections.emptyList();
    }

    public void vlozPredmet(Predmet predmet) {
    }

    public Predmet vezmiPredmet(String nazevPredmetu) {
        return null;
    }

    public boolean obsahujePredmet(String nazevPredmetu) {
        return false;
    }

    public void vlozPostavu(Postava postava) {
    }

    public Postava getPostava(String jmeno) {
        return null;
    }
}
