package dzikizachod;


import dzikizachod.Kolko;
import dzikizachod.gracz.PulaGracza;
import dzikizachod.WidokGracza;
import dzikizachod.Wydarzenie;
import dzikizachod.wydarzenie.*;

public abstract class Strategia {
    private int nr;

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    /**
     * Pokazanie wydarzenia graczowi
     * @param wydarzenie właśnie wydarzające sie wydarzenie
     */
    protected abstract void zobaczWydarzenie(Wydarzenie wydarzenie);

    /**
     *
     * @param widok aktualny stan kółka graczy
     * @param karty dostępne karty
     * @return wydarzenie, które gracz chce wykonać
     */
    protected Wydarzenie ruch(Kolko<WidokGracza> widok, PulaGracza karty) {
        if (karty.contains(Akcja.ULECZ)) {
            int cel = ulecz(widok, karty);
            if (cel != -1) {
                return new WydarzenieUlecz(nr, cel);
            }
        }
        if (karty.contains(Akcja.ZASIEG_PLUS_DWA)) {
            return new WydarzenieZasiegPlusDwa(nr);
        }
        if (karty.contains(Akcja.ZASIEG_PLUS_JEDEN)) {
            return new WydarzenieZasiegPlusJeden(nr);
        }
        if (karty.contains(Akcja.STRZEL)) {
            int cel = strzel(widok, karty);
            if (cel != -1) {
                return new WydarzenieStrzel(nr, cel);
            }
        }
        if (karty.contains(Akcja.DYNAMIT)) {
            if (dynamit(widok, karty)) {
                return new WydarzenieDynamit(widok.nastepnyZywy(nr));
            }
        }

        return null;
    }

    /**
     * @return czy położyć dynamit
     */
    protected abstract Boolean dynamit(Kolko<WidokGracza> widok, PulaGracza karty);

    /**
     * @return nr osoby do uleczenia / -1, gdy gracz nie chce leczyć
     */
    protected int ulecz(Kolko<WidokGracza> widok, PulaGracza karty) {
        return nr;
    }


    /**
     * @return nr osoby do tórej strzelić / -1, gdy gracz ei chce strzelać
     */
    protected abstract int strzel(Kolko<WidokGracza> widok, PulaGracza karty);

}
