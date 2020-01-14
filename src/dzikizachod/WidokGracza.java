package dzikizachod;


import dzikizachod.gracz.KlasaPostaci;
import dzikizachod.gracz.PrzekroczenieLimituPunktowZycia;

public class WidokGracza {
    private int zasieg;
    private int maxPunktyZycia;
    private int punktyZycia;
    private KlasaPostaci klasaPostaci;
    private int numer;

    public WidokGracza(int punktyZycia, KlasaPostaci klasaPostaci) {
        this.numer = -1;
        this.zasieg = 1;
        this.punktyZycia = punktyZycia;
        this.maxPunktyZycia = punktyZycia;
        this.klasaPostaci = klasaPostaci;
    }

    public WidokGracza(int zasieg, int punktyZycia, int maxPunktyZycia, KlasaPostaci klasaPostaci) {
        assert zasieg >= 1;
        assert punktyZycia <= maxPunktyZycia;
        assert punktyZycia >= 0;
        this.numer = -1;
        this.zasieg = zasieg;
        this.punktyZycia = punktyZycia;
        this.maxPunktyZycia = maxPunktyZycia;
        this.klasaPostaci = klasaPostaci;
    }

    public int getMaxPunktyZycia() {
        return maxPunktyZycia;
    }

    public int getPunktyZycia() {
        return punktyZycia;
    }

    public void setPunktyZycia(int punktyZycia) throws PrzekroczenieLimituPunktowZycia {
        if (punktyZycia >= maxPunktyZycia) {
            this.punktyZycia = maxPunktyZycia;
            throw new PrzekroczenieLimituPunktowZycia(punktyZycia);
        }
        else if (punktyZycia < 0) {
            this.punktyZycia = 0;
            throw new PrzekroczenieLimituPunktowZycia(punktyZycia);
        }
        else {
            this.punktyZycia = punktyZycia;
        }
    }

    public int getZasieg() {
        return zasieg;
    }

    public void setZasieg(int zasieg) {
        this.zasieg = zasieg;
    }

    public KlasaPostaci getKlasaPostaci() {
        return klasaPostaci;
    }

    /**
     * Ustawia numer gracza.
     * Można go ustawić tylko raz.
     * @param numer numers
     */
    public void ustawNumer(int numer) {
        if (this.numer == -1) {
            assert numer >= 0;
            this.numer = numer;
        }
    }



    public Boolean isMartwy() {
        return getPunktyZycia() == 0;
    }

    public Boolean czyWPelniZdrowy() {
        return getPunktyZycia() == getMaxPunktyZycia();
    }

    @Override
    public String toString() {
        if (isMartwy()) {
            return "X (" + getKlasaPostaci().toString() + ")";
        }
        else {
            return getKlasaPostaci().toString() + " (liczba żyć: " + getPunktyZycia() + ")";
        }
    }

}
