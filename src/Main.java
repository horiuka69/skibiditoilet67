import java.util.Scanner;

// Hlavni trida hry - konzolova aplikace
public class Main {

    // Spusti hru
    public static void main(String[] args) {
        Hra hra = new Hra();

        System.out.println(hra.vratUvitani());

        // Hlavni herni smycka
        Scanner scanner = new Scanner(System.in);
        while (!hra.konecHry()) {
            System.out.print("\n> ");
            String radek = scanner.nextLine();
            String odpoved = hra.zpracujPrikaz(radek);
            System.out.println(odpoved);
        }

        System.out.println(hra.vratEpilog());
        scanner.close();
    }
}