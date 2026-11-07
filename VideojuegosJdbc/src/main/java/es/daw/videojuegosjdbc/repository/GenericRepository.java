package es.daw.videojuegosjdbc.repository;

import java.util.List;
import java.util.Optional;

/** Operaciones CRUD comunes. T = entidad, ID = tipo de su clave primaria. */
public interface GenericRepository<T, ID> {

    void save(T entidad);

    Optional<T> findById(ID id);

    List<T> findAll();

    void update(T entidad);

    void delete(ID id);
}
