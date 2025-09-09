// Classe Elefante
public class Elefante {
    private String nome;
    private double peso;
    private double tamanho;

    public Elefante(String nome, double peso, double tamanho) {
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    public void andar() {
        System.out.println(nome + " está andando.");
    }

    public void beberAgua() {
        System.out.println(nome + " está bebendo água.");
    }

    public void abanar() {
        System.out.println(nome + " está abanando as orelhas.");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }
}

// Classe Pintura
public class Pintura {
    private String estilo;
    private String cores;
    private String tema;

    public Pintura(String estilo, String cores, String tema) {
        this.estilo = estilo;
        this.cores = cores;
        this.tema = tema;
    }

    public void aplicar() {
        System.out.println("Aplicando pintura no estilo " + estilo + " com tema " + tema);
    }

    public void remover() {
        System.out.println("Removendo pintura.");
    }

    public void descrever() {
        System.out.println("Pintura em estilo " + estilo + ", cores: " + cores + ", tema: " + tema);
    }

    // Getters e Setters
    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}

// Classe Artista
public class Artista {
    private String nome;
    private String nacionalidade;
    private String obra;

    public Artista(String nome, String nacionalidade, String obra) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.obra = obra;
    }

    public void criar() {
        System.out.println(nome + " está criando uma obra chamada " + obra);
    }

    public void pintar() {
        System.out.println(nome + " está pintando a obra " + obra);
    }

    public void pensar() {
        System.out.println(nome + " está pensando em novas ideias para suas artes.");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }
}
