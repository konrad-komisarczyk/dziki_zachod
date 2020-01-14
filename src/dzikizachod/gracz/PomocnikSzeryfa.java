package dzikizachod.gracz;


import dzikizachod.Gracz;
import dzikizachod.WidokGracza;
import dzikizachod.strategia.StrategiaPomocnikaSzeryfa;
import dzikizachod.strategia.StrategiaPomocnikaSzeryfaDomyslna;

import java.util.Random;

public class PomocnikSzeryfa extends Gracz {

    private StrategiaPomocnikaSzeryfa strategia;

    public PomocnikSzeryfa(Class rodzajStrategii) throws IllegalAccessException, InstantiationException {
        super((new Random()).nextInt(1) + 3, KlasaPostaci.POMOCNIK);
        this.strategia = (StrategiaPomocnikaSzeryfa) rodzajStrategii.newInstance();
    }

    public PomocnikSzeryfa() throws InstantiationException, IllegalAccessException {
        this(StrategiaPomocnikaSzeryfaDomyslna.class);
    }

    @Override
    public WidokGracza widok(Gracz patrzacy) {
        if (isMartwy()) {
            return new WidokGracza(getZasieg(), getPunktyZycia(), getMaxPunktyZycia(), getKlasaPostaci());
        }
        else {
            return new WidokGracza(getZasieg(), getPunktyZycia(), getMaxPunktyZycia(), KlasaPostaci.NIEZNANA);
        }
    }

}
