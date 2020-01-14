package dzikizachod;

import java.util.Collections;
import java.util.LinkedList;



public class PulaAkcji {

    private LinkedList<Akcja> niewykorzystane;
    private LinkedList<Akcja> wykorzystane;

    public PulaAkcji() {
        this.niewykorzystane = new LinkedList<Akcja>();
        this.wykorzystane = new LinkedList<Akcja>();
    }

    /**
     * Dodaje wskazanę liczbę akcji wskazanego typu do puli
     * @param typ typ akcji
     * @param liczba liczba
     */
    public void dodaj(Akcja typ, int liczba) {
        wykorzystane.add(liczba, typ);
    }

    public void dodaj(Akcja typ) {
        wykorzystane.add(typ);
    }

    /**
     * Tasuje pulę
     */
    private void tasuj() {
        niewykorzystane.addAll(wykorzystane);
        wykorzystane.clear();
        Collections.shuffle(niewykorzystane);
    }

    /**
     * Wyjmuje akcje z wierzchu puli. Zwrócona akcja zostaje usunięta z puli.
     * @return Akcja z wierzchu puli
     */
    Akcja wez() {
        if (niewykorzystane.isEmpty()) {
            tasuj();
        }
        Akcja w = niewykorzystane.pollFirst();
        return w;
    }

    PulaAkcji kopia() {
        PulaAkcji clone = new PulaAkcji();
        clone.niewykorzystane = (LinkedList<Akcja>) this.niewykorzystane.clone();
        clone.wykorzystane = (LinkedList<Akcja>) this.wykorzystane.clone();
        return clone;
    }
}
