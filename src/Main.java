import java.util.Scanner;

/**
 * 
 * Tato trida obsahuje vstupni bod programu a ridici smycku.
 */
public class Main {

    /**
     * Metoda main, ktera spusti celou hru.
     * Vytvori instanci hry a stara se o cteni vstupu od uzivatele.
     */
    public static void main(String[] args) {
        // Inicializace hry
        Hra hra = new Hra();

        // Vypis uvitani hraci
        System.out.println(hra.vratUvitani());

        // Hlavni herni smycka, bezi dokud hra neskonci nebo neni vstup
        Scanner scanner = new Scanner(System.in);
        while (!hra.konecHry() && scanner.hasNextLine()) {
            System.out.print("\n> ");
            // Cteni radku s prikazem od uzivatele
            String radek = scanner.nextLine();
            // Zpracovani prikazu logikou hry
            String odpoved = hra.zpracujPrikaz(radek);
            // Vypis vysledku prikazu
            System.out.println(odpoved);
        }

        // Vypis zavrecne zpravy po ukonceni smycky
        System.out.println(hra.vratEpilog());
        // Uzavreni scanneru pro cteni vstupu
        scanner.close();
    }
}