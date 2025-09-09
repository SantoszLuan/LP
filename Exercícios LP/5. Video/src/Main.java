// Classe Personagem
public class Personagem {
    private String nome;
    private int idade;
    private String sexo;

    public Personagem(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public void conversar() {
        System.out.println(nome + " está conversando.");
    }

    public void segurarMao(Personagem outro) {
        System.out.println(nome + " está segurando a mão de " + outro.getNome());
    }

    public void refletir() {
        System.out.println(nome + " está refletindo.");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

// Classe Prédio
public class Predio {
    private String cor;
    private double altura;
    private int andares;

    public Predio(String cor, double altura, int andares) {
        this.cor = cor;
        this.altura = altura;
        this.andares = andares;
    }

    public void subirAndares(int n) {
        System.out.println("Subindo " + n + " andares no prédio de cor " + cor);
    }

    public void acenderLuzes() {
        System.out.println("As luzes do prédio foram acesas.");
    }

    public void apagarLuzes() {
        System.out.println("As luzes do prédio foram apagadas.");
    }

    // Getters e Setters
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getAndares() {
        return andares;
    }

    public void setAndares(int andares) {
        this.andares = andares;
    }
}

// Classe Cidade
public class Cidade {
    private String nome;
    private int populacao;
    private double longitude;

    public Cidade(String nome, int populacao, double longitude) {
        this.nome = nome;
        this.populacao = populacao;
        this.longitude = longitude;
    }

    public void coletarImpostos() {
        System.out.println("A cidade de " + nome + " está coletando impostos.");
    }

    public void organizar() {
        System.out.println("A cidade de " + nome + " está se organizando.");
    }

    public void limpar() {
        System.out.println("A cidade de " + nome + " está sendo limpa.");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
