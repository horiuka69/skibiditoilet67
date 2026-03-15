# Dokumentace k prezentaci projektu: Adventura "Skibidi Toilet 67" (Talisman FC BZZ)

Tento dokument slouží jako podklad pro tvou prezentaci. Shnuje nejdůležitější aspekty kódu a logiky hry, aby ses v něm mohl snadno orientovat.

---

## 1. Jak projekt celkově funguje (Architektura)
Hra je postavena na principu **objektově orientovaného programování (OOP)** a využívá návrhový vzor **Command (Příkaz)**.

*   **Main**: Vstupní bod aplikace. Inicializuje hru a spouští hlavní smyčku.
*   **Hra**: Srdce aplikace, které propojuje herní plán a seznam příkazů. Zpracovává vstupy od hráče.
*   **HerniPlan**: Udržuje aktuální stav světa – kde se hráč nachází, zda jsou splněny určité podmínky (odemčená VIP lože) a stav hráče.
*   **Hrac a Batoh**: Reprezentují entitu hráče a jeho inventář.
*   **Mistnost**: Kontejner pro předměty, postavy a východy do sousedních místností.
*   **IPrikaz**: Rozhraní (Interface), které musí implementovat každý herní příkaz (jdi, vezmi, pouzij...). Díky tomu je snadné hru rozšiřovat.

## 2. Jak funguje GameData (gamedata.json)
Místo aby byl svět "zadrátovaný" přímo v Java kódu, je definován v externím souboru `res/gamedata.json`.
*   **Výhoda**: Můžeš změnit mapu, názvy předmětů nebo repliky postav bez jediného zásahu do kódu (změníš jen JSON).
*   **Struktura**: JSON obsahuje pole místností (`mistnosti`), kde každá má své předměty a postavy, a pole `propojeni`, které definuje graf mapy (odkud kam se dá jít).
*   **Načítání**: O načtení se stará třída `NacitacSveta`.

## 3. Herní smyčka (Game Loop)
Nachází se v souboru `Main.java`. Funguje na principu:
1.  **Vstup**: `scanner.nextLine()` počká na text od uživatele.
2.  **Zpracování**: Text se pošle do `hra.zpracujPrikaz(radek)`.
3.  **Výstup**: Výsledek z příkazu (např. "Šel jsi do šatny") se vypíše do konzole.
4.  **Podmínka ukončení**: Smyčka běží, dokud `hra.konecHry()` nevrátí `true`.

## 4. Logika hry a kritické části kódu

### Kritická část: `NacitacSveta.java`
Toto je technicky nejnáročnější část. Protože nepoužíváš externí knihovny pro JSON (jako GSON), je zde vytvořen **vlastní parser** pomocí regulárních výrazů (`Pattern`, `Matcher`).
*   Metoda `extrahujHodnotu` a `extrahujPole` pomocí regexů vyhledává klíče a hodnoty v textu JSONu.
*   Je to skvělé téma k hovoru – ukazuješ, že rozumíš práci se stringy a regulárními výrazy.

### Rozhodovací logika: `Hra.zpracujPrikaz`
Tato metoda v každém kroku kontroluje, zda se hráč nenachází v cílové místnosti (`Hriste`).
*   Pokud ano, hra končí.
*   Následně se vyhodnocuje výhra/prohra podle toho, zda má hráč v batohu "talisman".

---

## 5. Příkaz k naučení: `PrikazPouzij.java`
Doporučuji se detailně naučit tento soubor. Je v něm "to maso" herní logiky (hádanky).

**Proč o něm mluvit:**
*   Ukazuje **interakci mezi objekty**: Příkaz se ptá `HerniPlan`, ten se ptá `Hrac`, ten se ptá `Batoh`.
*   Obsahuje **herní flagy**: Např. `plan.setVipOdemceno(true)`. To je klíčové pro postup hrou.
*   **Příklad z kódu**:
    *   Když použiješ `klic` v místnosti `Bufet`, odemkne se VIP lože.
    *   Když použiješ `piti` v `Bufet`, fanynka Květuše ti poradí, kde je Hubert (posun v příběhu).

**Jak to funguje uvnitř (krok za krokem):**
1.  Zkontroluje, zda jsi zadal co chceš použít (`parametry.length == 0`).
2.  Zkontroluje, zda ten předmět skutečně máš v batohu.
3.  Získá název aktuální místnosti.
4.  Pomocí `if (nazev.equalsIgnoreCase(...))` spustí akci pro konkrétní předmět v konkrétní situaci.

---

### TIP pro prezentaci:
Pokud se tě učitel zeptá: *"Jak přidám nový příkaz?"*, odpověz:
1.  Vytvořím novou třídu, která implementuje `IPrikaz`.
2.  Naprogramuji metodu `proved`.
3.  V konstruktoru třídy `Hra` ji přidám do `seznamPrikazu` pomocí `vlozPrikaz`.
