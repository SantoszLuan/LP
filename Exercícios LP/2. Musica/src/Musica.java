public class Musica{
    // Atributos
    private String nome;
    private String estado;
    private String motivacao;

    // Construtor
    public Musica(String nome, String estado, String motivacao) {
        this.nome = nome;
        this.estado = estado;
        this.motivacao = motivacao;
    }

    // Método
    public void refletir() {
        System.out.println(nome + " está refletindo sobre sua vida.");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }
}

// Classe Momento
public class Momento {
    // Atributos
    private String tempo;
    private String situacao;
    private String intensidade;

    // Construtor
    public Momento(String tempo, String situacao, String intensidade) {
        this.tempo = tempo;
        this.situacao = situacao;
        this.intensidade = intensidade;
    }

    // Método
    public void viver() {
        System.out.println("Vivendo o momento: " + situacao + " com intensidade " + intensidade);
    }

    // Getters e Setters
    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }
}

// Classe Superacao
public class Superacao {
    // Atributos
    private String dificuldade;
    private String motivacao;
    private String aprendizado;

    // Construtor
    public Superacao(String dificuldade, String motivacao, String aprendizado) {
        this.dificuldade = dificuldade;
        this.motivacao = motivacao;
        this.aprendizado = aprendizado;
    }

    // Método
    public void vencer() {
        System.out.println("Superando a dificuldade: " + dificuldade +
                " com motivação " + motivacao +
                " e aprendizado: " + aprendizado);
    }

    // Getters e Setters
    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    public String getAprendizado() {
        return aprendizado;
    }

    public void setAprendizado(String aprendizado) {
        this.aprendizado = aprendizado;
    }
}