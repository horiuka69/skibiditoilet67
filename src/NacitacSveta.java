import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Nacita herni svet ze souboru JSON bez externich knihoven
public class NacitacSveta {

    public static Mistnost nacistSvet(String cestaKSouboru) throws IOException {
        String obsah = new String(Files.readAllBytes(Paths.get(cestaKSouboru)));

        Map<String, Mistnost> mistnosti = new HashMap<>();

        // 1. Najdeme blok mistnosti
        String mistnostiBlok = extrahujPole(obsah, "mistnosti");
        if (mistnostiBlok != null) {
            // Rozdelime na jednotlive objekty mistnosti - hledame zacatek objektu { "jmeno"
            // Protoze mistnosti mohou byt v poli oddeleny carkou, budeme hledat podle
            // klicu.
            List<String> objektyMistnosti = splitObjects(mistnostiBlok);
            for (String obj : objektyMistnosti) {
                String jmeno = extrahujHodnotu(obj, "jmeno");
                String popis = extrahujHodnotu(obj, "popis");

                if (jmeno != null) {
                    Mistnost mistnost = new Mistnost(jmeno, popis);

                    String predmetyStr = extrahujPole(obj, "predmety");
                    if (predmetyStr != null) {
                        for (String pObj : splitObjects(predmetyStr)) {
                            String pNazev = extrahujHodnotu(pObj, "nazev");
                            String pPopis = extrahujHodnotu(pObj, "popis");
                            boolean pPrenos = "true".equalsIgnoreCase(extrahujHodnotu(pObj, "prenositelny"));
                            if (pNazev != null) {
                                mistnost.vlozPredmet(new Predmet(pNazev, pPopis, pPrenos));
                            }
                        }
                    }

                    String postavyStr = extrahujPole(obj, "postavy");
                    if (postavyStr != null) {
                        for (String postObj : splitObjects(postavyStr)) {
                            String postJmeno = extrahujHodnotu(postObj, "jmeno");
                            String postPopis = extrahujHodnotu(postObj, "popis");
                            if (postJmeno != null) {
                                Postava postava = new Postava(postJmeno, postPopis);
                                String replikyStr = extrahujPole(postObj, "repliky");
                                if (replikyStr != null) {
                                    Pattern pReplika = Pattern.compile("\"([^\"]+)\"");
                                    Matcher mReplika = pReplika.matcher(replikyStr);
                                    while (mReplika.find()) {
                                        postava.pridejRepliku(mReplika.group(1));
                                    }
                                }
                                mistnost.vlozPostavu(postava);
                            }
                        }
                    }
                    mistnosti.put(jmeno, mistnost);
                }
            }
        }

        // 2. Nacteni propojeni
        String propojeniBlok = extrahujPole(obsah, "propojeni");
        if (propojeniBlok != null) {
            for (String propObj : splitObjects(propojeniBlok)) {
                String odkud = extrahujHodnotu(propObj, "odkud");
                String kam = extrahujHodnotu(propObj, "kam");
                if (odkud != null && kam != null) {
                    Mistnost mOdkud = mistnosti.get(odkud);
                    Mistnost mKam = mistnosti.get(kam);
                    if (mOdkud != null && mKam != null) {
                        mOdkud.setVychod(mKam);
                    }
                }
            }
        }

        // 3. Nacteni startu
        String startName = extrahujHodnotu(obsah, "start");
        if (startName != null && mistnosti.containsKey(startName)) {
            return mistnosti.get(startName);
        } else if (!mistnosti.isEmpty()) {
            return mistnosti.values().iterator().next();
        }
        return null;
    }

    private static String extrahujHodnotu(String json, String klic) {
        Pattern p = Pattern.compile("\"" + klic + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        // Zkusime najit boolean/cislo bez uvozovek
        p = Pattern.compile("\"" + klic + "\"\\s*:\\s*([^\\s,}\\]]+)");
        m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    private static String extrahujPole(String json, String klic) {
        int index = json.indexOf("\"" + klic + "\"");
        if (index == -1)
            return null;
        int zacatek = json.indexOf("[", index);
        if (zacatek == -1)
            return null;

        int hloubka = 0;
        for (int i = zacatek; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '[')
                hloubka++;
            else if (c == ']') {
                hloubka--;
                if (hloubka == 0) {
                    return json.substring(zacatek + 1, i);
                }
            }
        }
        return null;
    }

    private static List<String> splitObjects(String poleStr) {
        List<String> objekty = new ArrayList<>();
        int hloubka = 0;
        int zacatek = -1;
        for (int i = 0; i < poleStr.length(); i++) {
            char c = poleStr.charAt(i);
            if (c == '{') {
                if (hloubka == 0)
                    zacatek = i;
                hloubka++;
            } else if (c == '}') {
                hloubka--;
                if (hloubka == 0 && zacatek != -1) {
                    objekty.add(poleStr.substring(zacatek, i + 1));
                    zacatek = -1;
                }
            }
        }
        return objekty;
    }
}
