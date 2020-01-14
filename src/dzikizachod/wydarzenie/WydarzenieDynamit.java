package dzikizachod.wydarzenie;


import dzikizachod.*;
import dzikizachod.Gracz;
import dzikizachod.gracz.PrzekroczenieLimituPunktowZycia;

import java.util.Random;

public class WydarzenieDynamit extends Wydarzenie {
    private Random kostka;

    public WydarzenieDynamit(int celNr) {
        super(-1, celNr, Akcja.DYNAMIT);
        this.kostka = new Random();
    }

    @Override
    protected WydarzenieDynamit kopia() {
        return new WydarzenieDynamit(getCelNr());
    }

    @Override
    public Boolean czyPoprawne(Gracze gracze) {
        return (getCelNr() < gracze.size())
                && !gracze.get(getCelNr()).isMartwy();
    }

    @Override
    public void obsluz(Gra gra, int aktualnyGracz) throws Zwyciestwo, NiepoprawneWydarzenie {
        Gracze gracze = gra.getGracze();
        if (this.czyPoprawne(gracze)) {
            Gracz cel = gracze.get(getCelNr());
            if (getCelNr() == aktualnyGracz) {
                if (kostka.nextInt(6) == 1) {
                    //dynamit wybucha
                    gra.getPrinter().println("Dynamit: WYBUCHŁ");
                    try {
                        cel.setPunktyZycia(Math.max(0, cel.getPunktyZycia() - 3));
                    } catch (PrzekroczenieLimituPunktowZycia e) {
                        e.printStackTrace();
                    }
                    if (cel.isMartwy()) {
                        gra.pogrzeb(getCelNr());
                    }

                    //usuwamy dynamit
                    gra.setDynamit(null);
                }
                else {
                    //przekladamy dynamit
                    gra.getPrinter().println("Dynamit: PRZECHODZI DALEJ");
                    this.setCelNr(gracze.nastepnyZywy(getCelNr()));
                }

            }
            else if (gracze.nastepnyZywy(aktualnyGracz) == getCelNr() && gra.getDynamit() == null)  {
                //położenie dynamitu
                gra.setDynamit(this);
            }
        }
        else {
            throw new NiepoprawneWydarzenie();
        }

    }

    public String toString() {
        return getTyp().toString();
    }


}
