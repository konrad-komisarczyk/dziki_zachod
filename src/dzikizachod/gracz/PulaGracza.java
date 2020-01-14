package dzikizachod.gracz;

import dzikizachod.Akcja;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Klasa reprezentująca karty znajdujące sie w ręce gracza.
 * Jest kolejką priorytetową z ograniczoną liczbą elementów.
 * Kolejność elementów w kolejce jest tą, w jakiej gracze wykonują akcje.
 */
public class PulaGracza extends PriorityQueue<Akcja> {
    private static int MAX_KART = 5;

    public PulaGracza() {
        super(new Comparator<Akcja>() {

            private int priorytet(Akcja a) {
                switch (a) {
                    case ULECZ:
                        return 1;
                    case ZASIEG_PLUS_DWA:
                        return 2;
                    case ZASIEG_PLUS_JEDEN:
                        return 2;
                    case STRZEL:
                        return 3;
                    case DYNAMIT:
                        return 4;
                    default:
                        return 0;
                }
            }

            @Override
            public int compare(Akcja akcja, Akcja t1) {
                return priorytet(akcja) - priorytet(t1);
            }
        });
    }

    public Boolean isPrzepelniona() {
        return size() >= MAX_KART;
    }

    @Override
    public boolean add(Akcja akcja) {
        assert (size() + 1 <= MAX_KART);
        return super.add(akcja);
    }

    @Override
    public boolean addAll(Collection<? extends Akcja> collection) {
        assert (size() + collection.size() <= MAX_KART);
        return super.addAll(collection);
    }

    public PulaGracza kopia() {
        try {
            return (PulaGracza) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
