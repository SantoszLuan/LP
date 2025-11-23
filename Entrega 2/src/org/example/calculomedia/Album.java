package org.example.calculomedia;

public class Album {
    private String titulo;
    private int anoLancamento;
    private int numeroDeFaixas;

    public Album(String titulo, int anoLancamento, int numeroDeFaixas) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.numeroDeFaixas = numeroDeFaixas;
    }

    public String getTitulo() { return titulo; }
    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public int getNumeroDeFaixas() { return numeroDeFaixas; }
    public void setNumeroDeFaixas(int numeroDeFaixas) { this.numeroDeFaixas = numeroDeFaixas; }

    @Override
    public String toString() {
        return "Album{Título: " + titulo + ", Lançamento: " + anoLancamento + ", Faixas: " + numeroDeFaixas + "}";
    }
}