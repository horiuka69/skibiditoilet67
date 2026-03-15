# Fotbalová Adventura

## Popis hry
Tato hra je textová adventura, ve které se vžijete do role fotbalisty týmu FC BZZ. Před nejdůležitějším finále sezóny se vám ztratil váš rodinný talisman – Zlatá kopačka. Bez něj se cítíte jen jako poloviční hráč a vašemu týmu hrozí prohra. Vaším úkolem je projít útroby stadionu, promluvit si se spoluhráči a personálem, najít klíč k VIP loži a získat svůj talisman zpět dříve, než začne zápas.

## Ovládání hry
Mezi základní příkazy patří:

*   **jdi [místnost]** – Přesune vás do sousední místnosti (př. `jdi Chodba`).
*   **prozkoumej [předmět/postava]** – Vypíše podrobný popis věci nebo osoby. Může odhalit skryté předměty (př. `prozkoumej mice`).
*   **vezmi [předmět]** – Sebere předmět v místnosti a vloží ho do vašeho batohu (př. `vezmi klic`).
*   **poloz [předmět]** – Vyndá předmět z batohu a položí ho v aktuální místnosti.
*   **mluv [postava]** – Zahájí rozhovor s postavou v místnosti (př. `mluv Trener`).
*   **dej [předmět] [postava]** – Předá předmět z vašeho batohu postavě (př. `dej piti Kvetuse`).
*   **pouzij [předmět]** – Použije předmět z batohu (př. `pouzij klic` v bufetu pro odemčení VIP lože).
*   **inventar** – Vypíše obsah vašeho batohu.
*   **napoveda** – Poskytne radu, co byste měli v dané situaci dělat.
*   **pomoc** – Vypíše seznam všech dostupných příkazů.
*   **konec** – Ukončí hru.

## Herní mechaniky
*   **Batoh**: Máte omezenou kapacitu (3 předměty). Pokud je plný, musíte něco položit, abyste mohli vzít jinou věc.
*   **Interakce**: Postavy jsou klíčové pro získání informací. Trenér vám napoví, kde hledat klíč, spoluhráč Mlzan vám řekne, kdo má talisman, a Kvetuse v bufetu vám po uplacení pitím potvrdí Hubertovu polohu.
*   **Zámky**: Některé lokace (VIP lože) jsou zpočátku zamčené a vyžadují klíč.
*   **Vítězství**: Hru vyhrajete, pokud dorazíte na hřiště s talismanem v batohu.

## Návod k vítězství (Walkthrough)
Abyste hru úspěšně dokončili, postupujte podle tohoto scénáře:

1.  `mluv trener` (Dozvíte se o podezřelém pohybu u skladu)
2.  `jdi chodba`
3.  `mluv mlzan` (Dozvíte se, že talisman má asi Hubert ve VIP zone)
4.  `jdi sklad`
5.  `prozkoumej mice` (Najdete schovaný klíč)
6.  `vezmi klic`
7.  `jdi chodba`
8.  `jdi kancelar`
9.  `vezmi piti` (Seberete pivo pro fanynku)
10. `jdi chodba`
11. `jdi bufet`
12. `dej piti kvetuse` (Uplatíte ji a ona potvrdí, že Hubert je ve VIP loži)
13. `pouzij klic` (Odemknete VIP loži)
14. `jdi vip_loze`
15. `vezmi talisman` (Konečně máte svůj talisman!)
16. `jdi hriste` (Vstup na hřiště a vítězství!)

*: Příkazy jsou necitlivé na velká a malá písmena. Můžete psát `jdi chodba` i `Jdi Chodba`.*

## Technické informace

*   **Správa dat**: Herní svět (místnosti, předměty, postavy) je definován v souboru `res/gamedata.json` a načítán pomocí vlastního parseru v třídě `NacitacSveta`.
