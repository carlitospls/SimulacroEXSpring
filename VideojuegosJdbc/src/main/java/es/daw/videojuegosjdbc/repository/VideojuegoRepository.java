package es.daw.videojuegosjdbc.repository;

import es.daw.videojuegosjdbc.model.Videojuego;

/** Repositorio de videojuegos: CRUD genérico + consultas propias. */
public interface VideojuegoRepository extends GenericRepository<Videojuego, Integer> {

    /** ¿Hay algún videojuego de esta plataforma? */
    boolean existsByPlataformaCodigo(Integer codigoPlataforma);
}
