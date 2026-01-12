    //Rozhraní definující metody pro všechny příkazy ve hře.

public interface IPrikaz {

    // Metoda pro provedení příkazu.


    String proved(String[] parametry);

    /**
     * Vrátí název příkazu.
       return název příkazu
     */
    String getNazev();
}
