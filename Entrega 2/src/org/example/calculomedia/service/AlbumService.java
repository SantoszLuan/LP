package org.example.calculomedia.service;

import org.example.calculomedia.dao.DAO;
import org.example.calculomedia.Album;
import java.util.ArrayList;
import java.util.List;

public class AlbumService implements DAO<Album> {
    private static final List<Album> REPOSITORIO_ALBUNS = new ArrayList<>();

    @Override
    public void salvar(Album album) {
        REPOSITORIO_ALBUNS.add(album);
    }

    @Override
    public Album buscarPorId(String tituloAlbum) {
        return REPOSITORIO_ALBUNS.stream()
                .filter(a -> a.getTitulo().equals(tituloAlbum))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Album albumAtualizado) {
        Album albumExistente = buscarPorId(albumAtualizado.getTitulo());
        if (albumExistente != null) {
            albumExistente.setAnoLancamento(albumAtualizado.getAnoLancamento());
            albumExistente.setNumeroDeFaixas(albumAtualizado.getNumeroDeFaixas());
        }
    }

    @Override
    public void deletar(Album album) {
        REPOSITORIO_ALBUNS.removeIf(a -> a.getTitulo().equals(album.getTitulo()));
    }

    @Override
    public List<Album> buscarTodos() {
        return new ArrayList<>(REPOSITORIO_ALBUNS);
    }
}