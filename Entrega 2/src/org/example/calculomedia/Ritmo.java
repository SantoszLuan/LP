public class Ritmo {
    private int id;
    private String ouvir;
    private String palavras;
    private String vazio;

    public Ritmo(int id, String ouvir, String palavras, String vazio) {
        this.id = id;
        this.ouvir = ouvir;
        this.palavras = palavras;
        this.vazio = vazio;
    }
    public Ritmo(String ouvir, String palavras, String vazio) {
        this(0, ouvir, palavras, vazio);
    }

    public boolean parecerFirme() {
        return this.palavras != null && this.palavras.length() > 5;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOuvir() { return ouvir; }
    public void setOuvir(String ouvir) { this.ouvir = ouvir; }
    public String getPalavras() { return palavras; }
    public void setPalavras(String palavras) { this.palavras = palavras; }
    public String getVazio() { return vazio; }
    public void setVazio(String vazio) { this.vazio = vazio; }
}