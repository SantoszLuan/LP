public class Voz {
    private int id;
    private String tom;
    private String revolta;
    private int melhoria;

    public Voz(int id, String tom, String revolta, int melhoria) {
        this.id = id;
        this.tom = tom;
        this.revolta = revolta;
        this.melhoria = melhoria;
    }

    public Voz(String tom, String revolta, int melhoria) {
        this(0, tom, revolta, melhoria);
    }

    public String ouvirTom() {
        return "Tom: " + this.tom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTom() { return tom; }
    public void setTom(String tom) { this.tom = tom; }
    public String getRevolta() { return revolta; }
    public void setRevolta(String revolta) { this.revolta = revolta; }
    public int getMelhoria() { return melhoria; }
    public void setMelhoria(int melhoria) { this.melhoria = melhoria; }
}