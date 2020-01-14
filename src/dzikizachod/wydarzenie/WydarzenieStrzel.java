package dzikizachod.wydarzenie;


import dzikizachod.*;
import dzikizachod.Gracz;
import dzikizachod.gracz.PrzekroczenieLimituPunktowZycia;


public class WydarzenieStrzel extends Wydarzenie {

    public WydarzenieStrzel(int zleceniodawcaNr, int celNr) {
        super(zleceniodawcaNr, celNr, Akcja.STRZEL);
    }

    @Override
    protected WydarzenieStrzel kopia() {
        return new WydarzenieStrzel(getZleceniodawcaNr(), getCelNr());
    }


    @Override
    public Boolean czyPoprawne(Gracze gracze) {
        return (getCelNr() < gracze.size())
                && (getZleceniodawcaNr() < gracze.size())
                && !gracze.get(getCelNr()).isMartwy()
                && !gracze.get(getZleceniodawcaNr()).isMartwy()
                && (getCelNr() != getZleceniodawcaNr())
                && (gracze.czyWZasiegu(getZleceniodawcaNr(), getCelNr()));
    }

    @Override
    public void obsluz(Gra gra, int aktualnyGracz) throws Zwyciestwo, NiepoprawneWydarzenie {
        Gracze gracze = gra.getGracze();
        if (this.czyPoprawne(gracze) && (getZleceniodawcaNr() != aktualnyGracz)) {
            Gracz cel = gracze.get(getCelNr());
            try {
                cel.setPunktyZycia(cel.getPunktyZycia() - 1);
            } catch (PrzekroczenieLimituPunktowZycia e) {
                e.printStackTrace();
            }
            if (cel.isMartwy()) {
                gra.pogrzeb(getCelNr());
            }
        }
        else {
            throw new NiepoprawneWydarzenie();
        }

    }

    public String toString() {
        return getTyp().toString() + " " + getCelNr();
    }

}
