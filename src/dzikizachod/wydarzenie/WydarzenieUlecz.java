package dzikizachod.wydarzenie;

import dzikizachod.*;
import dzikizachod.Gracz;
import dzikizachod.gracz.PrzekroczenieLimituPunktowZycia;


public class WydarzenieUlecz extends Wydarzenie {

    public WydarzenieUlecz(int zleceniodawcaNr, int celNr) {
        super(zleceniodawcaNr, celNr, Akcja.ULECZ);
    }

    @Override
    protected WydarzenieUlecz kopia() {
        return new WydarzenieUlecz(getZleceniodawcaNr(), getCelNr());
    }

    @Override
    public Boolean czyPoprawne(Gracze gracze) {
        return (getCelNr() < gracze.size())
                && (getZleceniodawcaNr() < gracze.size())
                && !gracze.get(getCelNr()).isMartwy()
                && !gracze.get(getZleceniodawcaNr()).isMartwy()
                && !gracze.get(getCelNr()).czyWPelniZdrowy()
                && (gracze.czySasiedzi(getZleceniodawcaNr(), getCelNr()));
    }

    @Override
    public void obsluz(Gra gra, int aktualnyGracz) throws NiepoprawneWydarzenie {
        Gracze gracze = gra.getGracze();
        if (this.czyPoprawne(gracze)) {
            Gracz cel = gracze.get(getCelNr());
            try {
                cel.setPunktyZycia(cel.getPunktyZycia() + 1);
            } catch (PrzekroczenieLimituPunktowZycia e) {
                e.printStackTrace();
            }
        }
        else {
            throw new NiepoprawneWydarzenie();
        }

    }

    public String toString() {
        return getTyp().toString() + " " + ((getZleceniodawcaNr() == getCelNr()) ? "" : getCelNr());
    }


}
