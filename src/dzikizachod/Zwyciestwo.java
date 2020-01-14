package dzikizachod;



public class Zwyciestwo extends Throwable {
    private Druzyna druzyna;

    public Zwyciestwo(Druzyna druzyna) {
        this.druzyna = druzyna;
    }

    public String komunikatZwyciestwa() {
        return druzyna.toString();
    }
}
