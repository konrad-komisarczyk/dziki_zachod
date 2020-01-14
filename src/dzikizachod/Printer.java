package dzikizachod;



public class Printer {
    private int wciecie;

    Printer() {
        this.wciecie = 0;
    }

    public void setWciecie(int wciecie) {
        assert (wciecie >= 0);
        this.wciecie = wciecie;
    }

    public void zwiekszWciecie(int delta) {
        setWciecie(wciecie + delta);
    }

    public void println(String s) {
        for(int i = 0; i < wciecie; i++) {
            System.out.print(" ");
        }
        System.out.println(s);
    }

    public void println() {
        println("");
    }
}
