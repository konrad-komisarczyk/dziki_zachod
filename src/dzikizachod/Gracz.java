package dzikizachod;


import dzikizachod.gracz.KlasaPostaci;
import dzikizachod.gracz.PrzepelnieniePuliGracza;
import dzikizachod.gracz.PulaGracza;

public abstract class Gracz extends WidokGracza {
    private PulaGracza karty;
    private Strategia strategia;

    public Gracz(int punktyZycia, KlasaPostaci klasaPostaci) {
        super(punktyZycia, klasaPostaci);
        this.karty = new PulaGracza();
    }

    public PulaGracza getKarty() {
        return karty;
    }

    /**
     * Ustawia numer gracza.
     * Można go ustawić tylko raz.
     * @param numer numers
     */
    public void ustawNumer(int numer) {
        super.ustawNumer(numer);
        strategia.setNr(numer);
    }


    /**
     * Zwraca widok tego gracza z zewnątrz.
     * @param patrzacy inny gracz
     * @return to co widzi gracz @param patrzacy patrząc na tego gracza.
     */
    public abstract WidokGracza widok(Gracz patrzacy);

    void zobaczWydarzenie(Wydarzenie wydarzenie) {
        strategia.zobaczWydarzenie(wydarzenie);
    }

    public void dobierzKarte(Akcja a) throws PrzepelnieniePuliGracza {
        karty.add(a);
    }

    /**
     * @return czy gracz może jeszcze dobierać karty
     */
    public Boolean czyMaPelnaReke() {
        return karty.isPrzepelniona();
    }


    /**
     * Zapytanie gracza o kolejny ruch.
     * Jeżeli może wykonać jakiś ruch, przekazuje zapytanie strategii.
     * Dba o to, aby nie użyć karty, jakiej nie ma na ręce.
     * Odrzuca z ręki kartę, której używa.
     *
     * @param widok jak aktualnie gracz widzi innych graczy
     * @return ruch, jaki gracz chce aktualnie wykonać / null, gdy gracz chce zakończyć swoją turę (nie chce wykonać ruchu)
     */
    public Wydarzenie ruch(Kolko<WidokGracza> widok) {
        if (karty.isEmpty()) {
            return null;
        }
        else {
            Wydarzenie ruch = strategia.ruch(widok, karty.kopia());
            if (!karty.contains(ruch.getTyp())) {
                //TODO
            }
            else {
                karty.remove(ruch.getTyp());
            }

            return ruch;
        }
    }
}
