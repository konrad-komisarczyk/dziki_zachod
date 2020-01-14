package dzikizachod.gracz;


import dzikizachod.Gracz;
import dzikizachod.WidokGracza;
import dzikizachod.strategia.StrategiaSzeryfa;
import dzikizachod.strategia.StrategiaSzeryfaDomyślna;

public class Szeryf extends Gracz {

    private StrategiaSzeryfa strategia;

    public Szeryf(Class rodzajStrategii) throws IllegalAccessException, InstantiationException {
        super(5, KlasaPostaci.SZERYF);
        this.strategia = (StrategiaSzeryfa) rodzajStrategii.newInstance();
    }

    public Szeryf() throws InstantiationException, IllegalAccessException {
        this(StrategiaSzeryfaDomyślna.class);
    }


    @Override
    public WidokGracza widok(Gracz patrzacy) {
        return new WidokGracza(getZasieg(), getPunktyZycia(), getMaxPunktyZycia(), getKlasaPostaci());
    }

}
