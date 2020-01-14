package dzikizachod;


import dzikizachod.gracz.Bandyta;
import dzikizachod.gracz.PrzepelnieniePuliGracza;
import dzikizachod.gracz.Szeryf;
import dzikizachod.wydarzenie.NiepoprawneWydarzenie;

import java.util.List;

public class Gra {
    private Gracze gracze;
    private PulaAkcji pulaAkcji;
    private Printer printer;

    /**
     * Aktualnie znajdujący się na stole dynamit / null, jeżeli nie ma na stole dynamitu.
     * Wydarzenie jest obsługiwane na początku tury każdgo gracza.
     */
    private Wydarzenie dynamit;

    public Gracze getGracze() {
        return gracze;
    }

    public Printer getPrinter() {
        return printer;
    }

    public Wydarzenie getDynamit() {
        return dynamit;
    }

    public void setDynamit(Wydarzenie dynamit) {
        this.dynamit = dynamit;
    }


    /**
     * Sprawdza, czy w grze jest jeszcze jakiś żywy bandyta.
     * Złoż. czasowa O(gracze.size())
     */
    private Boolean czyJakisBandytaZyje() {
        for (Gracz gracz : gracze) {
            if (!gracz.isMartwy() && (gracz instanceof Bandyta)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Procedura wykonywana w przypadku śmierci gracza.
     * - Zwraca do puli karty zmarłego.
     * - Sprawdza, czy jego śmierć nie powoduje zwycięstwa którejś ze stron
     * @param nr numer gracza, który umarł.
     * @throws Zwyciestwo gdy śmierć tego gracza spoodowała zwycięstwo którejś ze stron
     */
    public void pogrzeb(int nr) throws Zwyciestwo {
        Gracz gracz = gracze.get(nr);

        for (Akcja a : gracz.getKarty()) {
            pulaAkcji.dodaj(a);
            gracz.getKarty().remove(a);
        }

        if (gracz instanceof Szeryf) {
            throw new Zwyciestwo(Druzyna.BANDYCI);
        }

        if (gracz instanceof Bandyta) {
            if (!czyJakisBandytaZyje()) {
                throw new Zwyciestwo(Druzyna.SZERYF_I_POMOCNICY);
            }
        }
    }

    /**
     * Rozgrywa turę gracza.
     * 1. gracz dobiera karty
     * 2. jeżeli w grze jest dynamit, może o wybuchnąć
     * 3. gracz może wykonywać ruchy, aż zdecyduje się przestać, lub skończą mu sie karty
     * @param nr numer gracza
     * @throws Zwyciestwo gdy w turze tego gracza jedna ze stron zwyciężyła
     */
    private void rozegrajTuręGracza(int nr) throws Zwyciestwo {
        Gracz gracz = gracze.get(nr);

        printer.println("GRACZ " + (nr+1) + " (" + gracz.getKlasaPostaci().toString() + ")");
        printer.zwiekszWciecie(2);

        if (gracz.isMartwy()) {
            printer.println("MARTWY");
        }
        else {
            //Gracz dobiera karty
            while (!gracz.czyMaPelnaReke()) {
                try {
                    gracz.dobierzKarte(pulaAkcji.wez());
                } catch (PrzepelnieniePuliGracza e) {
                    e.printStackTrace();
                }
            }
            printer.println("Akcje: " + gracz.getKarty().toString());

            //Jeżeli dynamit jest na stole może wybuchnąć
            if (dynamit != null) {
                try {
                    dynamit.obsluz(this, nr);
                } catch (NiepoprawneWydarzenie niepoprawnyRuch) {
                    niepoprawnyRuch.printStackTrace();
                }
            }

            //Gracz wykonuje kolejno ruchy
            printer.println("Ruchy:");
            printer.zwiekszWciecie(2);

            Wydarzenie ruch = gracz.ruch(gracze.widok(gracz));
            while (ruch != null) {
                try {
                    //wydarza się wydarzenie
                    printer.println(ruch.toString());
                    ruch.obsluz(this, nr);

                    //karta wraca do puli
                    if (ruch.getTyp() != Akcja.DYNAMIT) {
                        pulaAkcji.dodaj(ruch.getTyp());
                    }

                    ruch = gracz.ruch(gracze.widok(gracz));
                } catch (NiepoprawneWydarzenie niepoprawnyRuch) {
                    niepoprawnyRuch.printStackTrace();
                }
            }

            printer.zwiekszWciecie(-2);

        }
        printer.zwiekszWciecie(-2);

        printer.println();
        gracze.print(printer);
        printer.println();
    }

    /**
     * Wykonuje kolejno turę każdego gracza.
     * @param nr numer tury
     * @throws Zwyciestwo gdy w turze jedna ze stron zwyciężyła
     */
    private void rozegrajTure(int nr) throws Zwyciestwo {
        printer.println("** TURA " + nr);
        printer.zwiekszWciecie(2);
        for (int i = 0; i < gracze.size(); i++) {
            rozegrajTuręGracza(i);
        }
        printer.zwiekszWciecie(-2);
    }

    /**
     * Rozgrywa grę.
     *
     * @param gracze lista graczy biorących udział w grze, nie modyfikuje jej
     * @param pulaAkcji pula akcji biorących udział w grze, nie modyfikuje jej
     */
    public void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji) {
        //inicjalizacja rozgrywki
        this.printer = new Printer();
        this.dynamit = null;
        this.pulaAkcji = pulaAkcji.kopia();
        this.gracze = (Gracze) gracze; //TODO - klonowanie
        this.gracze.ustawLosowo();

        //start
        for(int i = 0; i < this.gracze.size(); i++) {
            gracze.get(i).ustawNumer(i);
        }
        printer.println("** START");
        printer.zwiekszWciecie(2);
        this.gracze.print(printer);
        printer.zwiekszWciecie(-2);

        //kolejne rozgrywanie tur
        try {
            for (int i = 1; i <= 42; i++) {
                rozegrajTure(i);
            }

            printer.println("** KONIEC");
            printer.zwiekszWciecie(2);
            printer.println("REMIS - OSIĄGNIĘTO LIMIT TUR");
            printer.zwiekszWciecie(-2);
        }
        catch (Zwyciestwo z) {
            printer.zwiekszWciecie(-2);
            printer.println();
            this.gracze.print(printer);
            printer.println();
            printer.println("** KONIEC");
            printer.zwiekszWciecie(2);
            printer.println(z.komunikatZwyciestwa());
            printer.zwiekszWciecie(-2);
        }
    }

}
