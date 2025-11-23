package org.example.calculomedia;

public class Musica {
    private String nome;
    private String artista;
    private int duracaoEmSegundos;

    public Musica(String nome, String artista, int duracaoEmSegundos) {
        this.nome = nome;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos; // CORRIGIDO
    }

    public String getNome() { return nome; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getDuracaoEmSegundos() { return duracaoEmSegundos; }
    public void setDuracaoEmSegundos(int duracaoEmSegundos) { this.duracaoEmSegundos = duracaoEmSegundos; }

    @Override
    public String toString() {
        return "Musica{Nome: " + nome + ", Artista: " + artista + ", Duração: " + duracaoEmSegundos + "s}";
    }
}