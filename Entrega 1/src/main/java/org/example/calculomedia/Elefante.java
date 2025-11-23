package org.example.calculomedia;

public class Elefante implements Registravel {
    private String nome;
    private double peso;
    private double tamanho;

    public Elefante(String nome, double peso, double tamanho) {
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Elefante," + nome + "," + peso + "," + tamanho;
    }
}