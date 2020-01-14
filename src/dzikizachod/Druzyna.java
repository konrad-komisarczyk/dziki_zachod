package dzikizachod;



public enum Druzyna {
    BANDYCI("bandyci"),
    SZERYF_I_POMOCNICY("szeryf i pomocnicy");

    private String nazwa;

    Druzyna(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}