package dzikizachod.gracz;



public enum KlasaPostaci {
    SZERYF("Szeryf"),
    BANDYTA("Bandyta"),
    POMOCNIK("Pomocnik Szeryfa"),
    NIEZNANA("");

    private String nazwa;

    KlasaPostaci(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
