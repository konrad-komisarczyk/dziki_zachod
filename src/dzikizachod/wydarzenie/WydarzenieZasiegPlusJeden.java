package dzikizachod.wydarzenie;

import dzikizachod.*;
import dzikizachod.Gracz;


public class WydarzenieZasiegPlusJeden extends Wydarzenie {

    public WydarzenieZasiegPlusJeden(int zleceniodawcaNr) {
        super(zleceniodawcaNr, -1, Akcja.ZASIEG_PLUS_JEDEN);
    }

    @Override
    protected WydarzenieZasiegPlusJeden kopia() {
        return new WydarzenieZasiegPlusJeden(getZleceniodawcaNr());
    }

    @Override
    public Boolean czyPoprawne(Gracze gracze) {
        return (getZleceniodawcaNr() < gracze.size())
                && !gracze.get(getZleceniodawcaNr()).isMartwy();
    }

    @Override
    public void obsluz(Gra gra, int aktualnyGracz) throws NiepoprawneWydarzenie {
        Gracze gracze = gra.getGracze();
        if (this.czyPoprawne(gracze) && (aktualnyGracz == getZleceniodawcaNr())) {
            Gracz cel = gracze.get(getZleceniodawcaNr());
            cel.setZasieg(cel.getZasieg() + 1);
        }
        else {
            throw new NiepoprawneWydarzenie();
        }

    }

    public String toString() {
        return getTyp().toString();
    }




}
