import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NacitacSveta {

    public static Mistnost nacistSvet(String cestaKSouboru) throws IOException {
        String obsah = new String(Files.readAllBytes(Paths.get(cestaKSouboru)));

        Map<String, Mistnost> mistnosti = new HashMap<>();

        // 1. Nacteni mistnosti
        // vzor: { "jmeno": "...", "popis": "..." }

        // Jednodušší regex pro tento specifický formát:
        Pattern pMistnost = Pattern
                .compile("\\{\\s*\"jmeno\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"popis\"\\s*:\\s*\"([^\"]+)\"\\s*\\}");
        Matcher mMistnost = pMistnost.matcher(obsah);
        while (mMistnost.find()) {
            String jmeno = mMistnost.group(1);
            String popis = mMistnost.group(2);
            mistnosti.put(jmeno, new Mistnost(jmeno, popis));
        }

        // 2. Načtení propojení
        // Hledáme vzor: { "odkud": "...", "kam": "..." }
        Pattern pPropojeni = Pattern
                .compile("\\{\\s*\"odkud\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"kam\"\\s*:\\s*\"([^\"]+)\"\\s*\\}");
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

        // 3. Načtení startovní místnosti
        Pattern pStart = Pattern.compile("\"start\"\\s*:\\s*\"([^\"]+)\"");
        Matcher mStart = pStart.matcher(obsah);
        String startName = null;
        if (mStart.find()) {
            startName = mStart.group(1);
        }

        if (startName != null && mistnosti.containsKey(startName)) {
            return mistnosti.get(startName);
        } else {
            // Fallback, pokud start není definován nebo nalezen, vrátíme první, nebo null
            if (!mistnosti.isEmpty()) {
                return mistnosti.values().iterator().next();
            }
            return null;
        }
    }
}
