package org.example.calculomedia;

public class Predio implements Registravel {
    private String cor;
    private double altura;
    private int andares;

    public Predio(String cor, double altura, int andares) {
        this.cor = cor;
        this.altura = altura;
        this.andares = andares;
    }

    @Override
    public String toString() {
        return "Predio," + cor + "," + altura + "," + andares;
    }
}