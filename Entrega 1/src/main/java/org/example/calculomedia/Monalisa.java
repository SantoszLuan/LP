package org.example.calculomedia;

public class Monalisa implements Registravel {
    private String nome;
    private int idade;
    private String sexo;

    public Monalisa(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Monalisa," + nome + "," + idade + "," + sexo;
    }
}