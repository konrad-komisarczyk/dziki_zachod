package dzikizachod.strategia;


import dzikizachod.Kolko;
import dzikizachod.gracz.PulaGracza;
import dzikizachod.WidokGracza;
import dzikizachod.Wydarzenie;

public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {
    @Override
    public void zobaczWydarzenie(Wydarzenie wydarzenie) {

    }

    @Override
    protected Wydarzenie ruch(Kolko<WidokGracza> widok, PulaGracza karty) {
        return null;
    }
}
