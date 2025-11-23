package org.example.calculomedia;

public class Registrável {
// Define que qualquer classe que a implemente precisa saber se transformar em texto (toString)
// Assim, podemos salvar qualquer objeto em um arquivo sem saber sua classe específica. Como se fosse um "crachá"
public interface Registravel {
    String toString();
}
}

