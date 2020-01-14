package dzikizachod;


import dzikizachod.gracz.KlasaPostaci;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasa reprezentująca graczy (lub widoki graczy) siedzących w kółku.
 * @param <E> widok gracza
 */
public class Kolko<E extends WidokGracza> extends ArrayList<E> {

    /**
     * Następny żywy gracz na prawo od danego.
     * @param a nr na kółku danego gracza
     * @return numer kolejnego żywego gracza
     */
    public int nastepnyZywy(int a) {
        int i = (a + 1) % size();
        while (get(i).isMartwy()) {
            i = (i + 1) % size();
        }
        return i;
    }

    /**
     * Następny żywy gracz na lewo od danego.
     * @param a nr na kółku danego gracza
     * @return numer poprzedniego żywego gracza
     */
    public int poprzedniZywy(int a) {
        int i = (a - 1 + size()) % size();
        while (get(i).isMartwy()) {
            i = (i - 1 + size()) % size();
        }
        return i;
    }

    /**
     * Zlicza żywych graczy idąc w prawo od jednego do drugiego
     * @param a nr na kółku jednego gracza
     * @param b nr na kółku drugiego gracza, musi być żywy
     * @return ile żywych graczy jest pomiędzy danymi graczami idąc w prawo
     */
    private int odlegloscRosnaco(int a, int b) {
        assert !get(b).isMartwy();
        int odl = 0;
        while (a != b) {
            a = nastepnyZywy(a);
            odl ++;
        }
        return odl;
    }

    public int znajdzSzeryfa() throws BrakSzeryfa {
        for(int i = 0; i < size(); i++) {
            if (get(i).getKlasaPostaci() == KlasaPostaci.SZERYF) {
                return i;
            }
        }

        throw new BrakSzeryfa();
    }

    /**
     * @param a nr na kółku pierwszego gracza, musi być żywy
     * @param b nr na kółku drugiego gracza, musi być żywy
     * @return ile conajmniej żywych graczy jest pomiędzy danymi graczami
     */
    private int odleglosc(int a, int b) {
        return Math.min(odlegloscRosnaco(a, b), odlegloscRosnaco(b, a));
    }

    /**
     * @param a nr na kółku pierwszego gracza, musi być żywy
     * @param b nr na kółku drugiego gracza, musi być żywy
     * @return czy gracze są sąsiadami
     */
    public Boolean czySasiedzi(int a, int b) {
        return (odleglosc(a, b) == 1);
    }

    /**
     * @param a nr na kółku pierwszego gracza, musi być żywy
     * @param cel nr na kółku drugiego gracza, musi być żywy
     * @return czy drugi gracz znajuje sie w zasięgu pierwszego
     */
    public Boolean czyWZasiegu(int a, int cel) {
        return (odleglosc(a, cel) <= this.get(a).getZasieg());
    }

    /**
     * Drukuje listę graczy
     * @param printer obiekt klasy Printer
     */
    void print(Printer printer) {
        printer.println("Gracze:");
        printer.zwiekszWciecie(2);
        for (int i = 0; i < this.size(); i++) {
            printer.println((i+1) + ": " + this.get(i).toString());
        }
        printer.zwiekszWciecie(-2);
    }

    /**
     * Losowo zmienia kolejność graczy na kółku.
     */
    void ustawLosowo() {
        Collections.shuffle(this);
    }
}
