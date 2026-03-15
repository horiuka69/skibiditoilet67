/**
 * Rozhrani definujici metody pro vsechny prikazy ve hre.
 * Kazdy novy prikaz musi implementovat toto rozhrani.
 */
public interface IPrikaz {

    /**
     * Metoda pro provedeni konkretniho prikazu.
     * @param parametry Pole parametru zadanych na konzoli.
     * @return Textova odpoved na provedeni prikazu.
     */
    String proved(String[] parametry);

    /**
     * Vraci nazev prikazu (slovo, na ktere prikaz reaguje).
     */
    String getNazev();
}
