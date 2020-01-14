package dzikizachod.wydarzenie;

import dzikizachod.*;
import dzikizachod.Gracz;


public class WydarzenieZasiegPlusDwa extends Wydarzenie {

    public WydarzenieZasiegPlusDwa(int zleceniodawcaNr) {
        super(zleceniodawcaNr, -1, Akcja.ZASIEG_PLUS_DWA);
    }

    @Override
    protected WydarzenieZasiegPlusDwa kopia() {
        return new WydarzenieZasiegPlusDwa(getZleceniodawcaNr());
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
            cel.setZasieg(cel.getZasieg() + 2);
        }
        else {
            throw new NiepoprawneWydarzenie();
        }

    }

    public String toString() {
        return getTyp().toString();
    }

}
