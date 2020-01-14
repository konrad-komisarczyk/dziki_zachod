package dzikizachod.gracz;



public class PrzekroczenieLimituPunktowZycia extends Exception {
    private int ile;

    public int getIle() {
        return ile;
    }

    public PrzekroczenieLimituPunktowZycia(int ile) {
        this.ile = ile;
    }
}
