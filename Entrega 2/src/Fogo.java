public class Fogo {
    private int id;
    private String sensacao;
    private String fim;
    private String recomecar;

    //Construtor (Lê dados do SQLite)
    public Fogo(int id, String sensacao, String fim, String recomecar) {
        this.id = id;
        this.sensacao = sensacao;
        this.fim = fim;
        this.recomecar = recomecar;
    }

    //Construtor simplificado (Usado para criar um novo item quando clicado em "Adicionar")
    public Fogo(String sensacao, String fim, String recomecar) {
        this(0, sensacao, fim, recomecar);
    }


    public String acharSensacao() {
        return "Sensação: " + this.sensacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSensacao() { return sensacao; }
    public void setSensacao(String sensacao) { this.sensacao = sensacao; }
    public String getFim() { return fim; }
    public void setFim(String fim) { this.fim = fim; }
    public String getRecomecar() { return recomecar; }
    public void setRecomecar(String recomecar) { this.recomecar = recomecar; }
}