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

    // Nacte svet z JSON souboru a vrati startovni mistnost
    public static Mistnost nacistSvet(String cestaKSouboru) throws IOException {
        String obsah = new String(Files.readAllBytes(Paths.get(cestaKSouboru)));

        Map<String, Mistnost> mistnosti = new HashMap<>();

        // 1. Nacteni mistnosti s predmety a postavami
        Pattern pMistnost = Pattern.compile(
                "\\{\\s*\"jmeno\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"popis\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"predmety\"\\s*:\\s*\\[([^\\]]*)\\]\\s*,\\s*\"postavy\"\\s*:\\s*\\[([^\\]]*)\\]\\s*\\}",
                Pattern.DOTALL);

        Matcher mMistnost = pMistnost.matcher(obsah);

        while (mMistnost.find()) {
            String jmeno = mMistnost.group(1);
            String popis = mMistnost.group(2);
            String predmetyStr = mMistnost.group(3);
            String postavyStr = mMistnost.group(4);

            Mistnost mistnost = new Mistnost(jmeno, popis);

            // Parsovani predmetu
            if (predmetyStr != null && !predmetyStr.trim().isEmpty()) {
                List<Predmet> predmety = parsujPredmety(predmetyStr);
                for (Predmet p : predmety) {
                    mistnost.vlozPredmet(p);
                }
            }

            // Parsovani postav
            if (postavyStr != null && !postavyStr.trim().isEmpty()) {
                List<Postava> postavy = parsujPostavy(postavyStr);
                for (Postava p : postavy) {
                    mistnost.vlozPostavu(p);
                }
            }

            mistnosti.put(jmeno, mistnost);
        }

        // 2. Nacteni propojeni
        Pattern pPropojeni = Pattern.compile(
                "\\{\\s*\"odkud\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"kam\"\\s*:\\s*\"([^\"]+)\"\\s*\\}");
        Matcher mPropojeni = pPropojeni.matcher(obsah);

        while (mPropojeni.find()) {
            String odkud = mPropojeni.group(1);
            String kam = mPropojeni.group(2);

            Mistnost mOdkud = mistnosti.get(odkud);
            Mistnost mKam = mistnosti.get(kam);

            if (mOdkud != null && mKam != null) {
                mOdkud.setVychod(mKam);
            }
        }

        // 3. Nacteni startu
        Pattern pStart = Pattern.compile("\"start\"\\s*:\\s*\"([^\"]+)\"");
        Matcher mStart = pStart.matcher(obsah);
        String startName = null;
        if (mStart.find()) {
            startName = mStart.group(1);
        }

        if (startName != null && mistnosti.containsKey(startName)) {
            return mistnosti.get(startName);
        } else {
            if (!mistnosti.isEmpty()) {
                return mistnosti.values().iterator().next();
            }
            return null;
        }
    }

    // Parsuje predmety z JSON retezce
    private static List<Predmet> parsujPredmety(String predmetyStr) {
        List<Predmet> predmety = new ArrayList<>();

        Pattern pPredmet = Pattern.compile(
                "\\{\\s*\"nazev\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"popis\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"prenositelny\"\\s*:\\s*(true|false)\\s*\\}",
                Pattern.DOTALL);

        Matcher m = pPredmet.matcher(predmetyStr);
        while (m.find()) {
            String nazev = m.group(1);
            String popis = m.group(2);
            boolean prenositelny = Boolean.parseBoolean(m.group(3));

            predmety.add(new Predmet(nazev, popis, prenositelny));
        }

        return predmety;
    }

    // Parsuje postavy z JSON retezce
    private static List<Postava> parsujPostavy(String postavyStr) {
        List<Postava> postavy = new ArrayList<>();

        Pattern pPostava = Pattern.compile(
                "\\{\\s*\"jmeno\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"popis\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"repliky\"\\s*:\\s*\\[([^\\]]*)\\]\\s*\\}",
                Pattern.DOTALL);

        Matcher m = pPostava.matcher(postavyStr);
        while (m.find()) {
            String jmeno = m.group(1);
            String popis = m.group(2);
            String replikyStr = m.group(3);

            Postava postava = new Postava(jmeno, popis);

            // Parsovani replik
            if (replikyStr != null && !replikyStr.trim().isEmpty()) {
                Pattern pReplika = Pattern.compile("\"([^\"]+)\"");
                Matcher mReplika = pReplika.matcher(replikyStr);
                while (mReplika.find()) {
                    postava.pridejRepliku(mReplika.group(1));
                }
            }

            postavy.add(postava);
        }

        return postavy;
    }
}
