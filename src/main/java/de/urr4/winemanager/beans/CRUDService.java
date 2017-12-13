package de.urr4.winemanager.beans;

import java.util.List;

public interface CRUDService<T> {

    List<T> getAll();

    T getById(Long id);

    void save(T t);

    void delete(T t);
}
