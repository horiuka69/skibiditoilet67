public class Hrac {

    private Mistnost aktualniMistnost;
    private Batoh batoh;

    public Hrac(Mistnost pocatecniMistnost) {
        this.aktualniMistnost = pocatecniMistnost;
        this.batoh = new Batoh();
    }

    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    public void setAktualniMistnost(Mistnost mistnost) {
        this.aktualniMistnost = mistnost;
    }

    public Batoh getBatoh() {
        return batoh;
    }
}
