import dzikizachod.*;
import dzikizachod.gracz.Bandyta;
import dzikizachod.Gracz;
import dzikizachod.gracz.PomocnikSzeryfa;
import dzikizachod.gracz.Szeryf;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        List<Gracz> gracze = new ArrayList<Gracz>();
        gracze.add(new Szeryf());
        for(int i=0;i<2;i++) gracze.add(new PomocnikSzeryfa());
        for(int i=0;i<3;i++) gracze.add(new Bandyta());

        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 20);
        pulaAkcji.dodaj(Akcja.STRZEL, 60);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 3);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 1);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);
        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }
}
