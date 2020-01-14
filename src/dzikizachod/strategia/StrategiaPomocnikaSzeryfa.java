package dzikizachod.strategia;


import dzikizachod.BrakSzeryfa;
import dzikizachod.Kolko;
import dzikizachod.Strategia;
import dzikizachod.WidokGracza;
import dzikizachod.gracz.PulaGracza;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {

    @Override
    protected int ulecz(Kolko<WidokGracza> widok, PulaGracza karty) {
        try {
            int szeryf = widok.znajdzSzeryfa();
            if (!widok.get(szeryf).czyWPelniZdrowy() && widok.czySasiedzi(getNr(), szeryf)) {
                return szeryf;
            }
            else if (!widok.get(getNr()).czyWPelniZdrowy()) {
                return getNr();
            }
        } catch (BrakSzeryfa brakSzeryfa) {
            brakSzeryfa.printStackTrace();
        }
        return -1;
    }
}
