package org.example.calculomedia.service;

import org.example.calculomedia.dao.DAO;
import org.example.calculomedia.Musica;
import java.util.ArrayList;
import java.util.List;

public class MusicaService implements DAO<Musica> {
    private static final List<Musica> REPOSITORIO_MUSICAS = new ArrayList<>();

    @Override
    public void salvar(Musica musica) {
        REPOSITORIO_MUSICAS.add(musica);
    }

    @Override
    public Musica buscarPorId(String nomeMusica) {
        return REPOSITORIO_MUSICAS.stream()
                .filter(m -> m.getNome().equals(nomeMusica))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Musica musicaAtualizada) {
        Musica musicaExistente = buscarPorId(musicaAtualizada.getNome());
        if (musicaExistente != null) {
            musicaExistente.setArtista(musicaAtualizada.getArtista());
            musicaExistente.setDuracaoEmSegundos(musicaAtualizada.getDuracaoEmSegundos());
        }
    }

    @Override
    public void deletar(Musica musica) {
        REPOSITORIO_MUSICAS.removeIf(m -> m.getNome().equals(musica.getNome()));
    }

    @Override
    public List<Musica> buscarTodos() {
        return new ArrayList<>(REPOSITORIO_MUSICAS);
    }
}