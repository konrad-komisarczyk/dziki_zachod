package dzikizachod.gracz;


import dzikizachod.Gracz;
import dzikizachod.WidokGracza;
import dzikizachod.strategia.StrategiaBandyty;
import dzikizachod.strategia.StrategiaBandytyDomyslna;

import java.util.Random;

public class Bandyta extends Gracz {

    private StrategiaBandyty strategia;

    public Bandyta(Class rodzajStrategii) throws IllegalAccessException, InstantiationException {
        super((new Random()).nextInt(1) + 3, KlasaPostaci.BANDYTA);
        this.strategia = (StrategiaBandyty) rodzajStrategii.newInstance();
    }

    public Bandyta() throws InstantiationException, IllegalAccessException {
        this(StrategiaBandytyDomyslna.class);
    }

    @Override
    public WidokGracza widok(Gracz patrzacy) {
        if (isMartwy() || (patrzacy instanceof Bandyta)) {
            return new WidokGracza(getZasieg(), getPunktyZycia(), getMaxPunktyZycia(), getKlasaPostaci());
        }
        else {
            return new WidokGracza(getZasieg(), getPunktyZycia(), getMaxPunktyZycia(), KlasaPostaci.NIEZNANA);
        }
    }
}
