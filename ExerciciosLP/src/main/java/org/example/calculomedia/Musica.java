package org.example.calculomedia;

public class Musica implements Registravel {
    private String nome;
    private String estado;
    private String motivacao;

    public Musica(String nome, String estado, String motivacao) {
        this.nome = nome;
        this.estado = estado;
        this.motivacao = motivacao;
    }

    @Override
    public String toString() {
        return "Musica," + nome + "," + estado + "," + motivacao;
    }
}