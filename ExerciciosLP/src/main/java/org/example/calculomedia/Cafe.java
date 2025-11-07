package org.example.calculomedia;

public class Cafe implements Registravel {
    private String tamanho;
    private String posicao;
    private String cor;

    public Cafe(String tamanho, String posicao, String cor) {
        this.tamanho = tamanho;
        this.posicao = posicao;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Cafe," + tamanho + "," + posicao + "," + cor;
    }
}