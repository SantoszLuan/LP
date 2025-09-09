// Classe Café
public class Cafe {
    private String tamanho;
    private String posicao;
    private String cor;

    public Cafe(String tamanho, String posicao, String cor) {
        this.tamanho = tamanho;
        this.posicao = posicao;
        this.cor = cor;
    }

    public void mexer() {
        System.out.println("Mexendo o café " + cor + " de tamanho " + tamanho);
    }

    // Getters e Setters
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}

// Classe Xícara
public class Xicara {
    private double largura;
    private double altura;
    private String conteudo;

    public Xicara(double largura, double altura, String conteudo) {
        this.largura = largura;
        this.altura = altura;
        this.conteudo = conteudo;
    }

    public void encher() {
        System.out.println("A xícara foi cheia com " + conteudo);
    }

    // Getters e Setters
    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

// Classe Rosto
public class Rosto {
    private String expressao;
    private String cor;
    private String formato;

    public Rosto(String expressao, String cor, String formato) {
        this.expressao = expressao;
        this.cor = cor;
        this.formato = formato;
    }

    public void desenhar() {
        System.out.println("Desenhando um rosto com expressão " + expressao + " e formato " + formato);
    }

    // Getters e Setters
    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}

// Classe Arte
public class Arte {
    private String estilo;
    private String tecnica;
    private String artista;

    public Arte(String estilo, String tecnica, String artista) {
        this.estilo = estilo;
        this.tecnica = tecnica;
        this.artista = artista;
    }

    public void mostrar() {
        System.out.println("Mostrando a arte no estilo " + estilo);
    }

    public void assinar() {
        System.out.println("Arte assinada por " + artista);
    }

    public void mudar(String novoEstilo) {
        this.estilo = novoEstilo;
        System.out.println("Estilo da arte alterado para " + novoEstilo);
    }

    // Getters e Setters
    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}

// Classe Ingrediente
public class Ingrediente {
    private String nome;
    private String tipo;
    private double quantidade;

    public Ingrediente(String nome, String tipo, double quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public void adicionar(double qtd) {
        this.quantidade += qtd;
        System.out.println("Adicionado " + qtd + " de " + nome);
    }

    public void remover(double qtd) {
        if (qtd <= quantidade) {
            this.quantidade -= qtd;
            System.out.println("Removido " + qtd + " de " + nome);
        } else {
            System.out.println("Quantidade insuficiente de " + nome);
        }
    }

    public void descrever() {
        System.out.println("Ingrediente: " + nome + ", Tipo: " + tipo + ", Quantidade: " + quantidade);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}

// Classe Obra
public class Obra {
    private String titulo;
    private int ano;
    private String material;

    public Obra(String titulo, int ano, String material) {
        this.titulo = titulo;
        this.ano = ano;
        this.material = material;
    }

    public void expor() {
        System.out.println("Expondo a obra: " + titulo + " (" + ano + ")");
    }

    public void restaurar() {
        System.out.println("A obra " + titulo + " está sendo restaurada.");
    }

    public void avaliar() {
        System.out.println("A obra " + titulo + " foi avaliada.");
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
