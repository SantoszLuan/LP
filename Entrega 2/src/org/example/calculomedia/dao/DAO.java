package org.example.calculomedia.dao;

import java.util.List;

public interface DAO<T> {
    void salvar(T objeto);
    T buscarPorId(String id);
    void atualizar(T objeto);
    void deletar(T objeto);
    List<T> buscarTodos();
}