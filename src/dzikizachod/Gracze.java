package dzikizachod;


/**
 * Klasa reprezentująca graczy siedzących w kółku.
 */
public class Gracze extends Kolko<Gracz> {

    /**
     * Zwraca, jak dany gracz widzi to kółko
     * @param patrzacy gracz należacy do kółka
     * @return widok kółka graczy z perspektywy danego gracza
     */
    Kolko<WidokGracza> widok(Gracz patrzacy) {
        Kolko<WidokGracza> w = new Kolko<>();
        for(Gracz gracz : this) {
            w.add(gracz.widok(patrzacy));
        }
        return w;
    }
}
