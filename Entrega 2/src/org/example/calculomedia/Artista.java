package org.example.calculomedia;

public class Artista {

    private String nomeCompleto;
    private String nacionalidade;
    private int anoInicioCarreira;


    public Artista(String nomeCompleto, String nacionalidade, int anoInicioCarreira) {
        this.nomeCompleto = nomeCompleto;
        this.nacionalidade = nacionalidade;
        this.anoInicioCarreira = anoInicioCarreira;
    }


    public String getNomeCompleto() { return nomeCompleto; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public int getAnoInicioCarreira() { return anoInicioCarreira; }
    public void setAnoInicioCarreira(int anoInicioCarreira) { this.anoInicioCarreira = anoInicioCarreira; }

    @Override
    public String toString() {
        return "Artista{Nome: " + nomeCompleto + ", País: " + nacionalidade + ", Início: " + anoInicioCarreira + "}";
    }
}