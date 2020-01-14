package dzikizachod;


import dzikizachod.Akcja;
import dzikizachod.Gra;
import dzikizachod.Gracze;
import dzikizachod.Zwyciestwo;
import dzikizachod.wydarzenie.NiepoprawneWydarzenie;


public abstract class Wydarzenie {
    private int zleceniodawcaNr;
    private int celNr;
    private Akcja typ;

    public Wydarzenie(int zleceniodawcaNr, int celNr, Akcja typ) {
        this.zleceniodawcaNr = zleceniodawcaNr;
        this.celNr = celNr;
        this.typ = typ;
    }

    public int getZleceniodawcaNr() {
        return zleceniodawcaNr;
    }

    public int getCelNr() {
        return celNr;
    }

    public Akcja getTyp() {
        return typ;
    }

    protected void setZleceniodawcaNr(int zleceniodawcaNr) {
        this.zleceniodawcaNr = zleceniodawcaNr;
    }

    protected void setCelNr(int celNr) {
        this.celNr = celNr;
    }

    protected abstract Wydarzenie kopia();

    /**
     * Sprawdza czy wydarzenie jest poprawne.
     *
     * @param gracze gracze
     */
    protected abstract Boolean czyPoprawne(Gracze gracze);


    /**
     * Przeprowadza wydarzenie.
     * Dba o to, aby niepoprawne wydarzenie nie zostało przeprowadzone.
     * Pokazuje wszystkim graczom wydarzenie.
     * @param aktualnyGracz gracz, w którego turze jesteśmy
     * @throws Zwyciestwo gdy wydarzenie zapewnia zwyciestwo którejś ze stron
     * @throws NiepoprawneWydarzenie gdy wydarzenie jest niepoprawne
     */
    protected abstract void obsluz(Gra gra, int aktualnyGracz) throws Zwyciestwo, NiepoprawneWydarzenie;

}
