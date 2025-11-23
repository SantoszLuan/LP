package org.example.calculomedia.service;

import org.example.calculomedia.dao.DAO;
import org.example.calculomedia.Artista;
import java.util.ArrayList;
import java.util.List;

public class ArtistaService implements DAO<Artista> {
    private static final List<Artista> REPOSITORIO_ARTISTAS = new ArrayList<>();

    @Override
    public void salvar(Artista artista) {
        REPOSITORIO_ARTISTAS.add(artista);
    }

    @Override
    public Artista buscarPorId(String nomeCompleto) {
        return REPOSITORIO_ARTISTAS.stream()
                .filter(a -> a.getNomeCompleto().equals(nomeCompleto))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Artista artistaAtualizado) {
        Artista artistaExistente = buscarPorId(artistaAtualizado.getNomeCompleto());
        if (artistaExistente != null) {
            artistaExistente.setNacionalidade(artistaAtualizado.getNacionalidade());
            artistaExistente.setAnoInicioCarreira(artistaAtualizado.getAnoInicioCarreira());
        }
    }

    @Override
    public void deletar(Artista artista) {
        REPOSITORIO_ARTISTAS.removeIf(a -> a.getNomeCompleto().equals(artista.getNomeCompleto()));
    }

    @Override
    public List<Artista> buscarTodos() {
        return new ArrayList<>(REPOSITORIO_ARTISTAS);
    }
}