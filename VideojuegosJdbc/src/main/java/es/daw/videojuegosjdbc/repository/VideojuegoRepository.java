package es.daw.videojuegosjdbc.repository;

import es.daw.videojuegosjdbc.model.Videojuego;

/** Repositorio de videojuegos: CRUD genérico + consultas propias. */
public interface VideojuegoRepository extends GenericRepository<Videojuego, Integer> {

    /** ¿Hay algún videojuego de esta plataforma? */
    boolean existsByPlataformaCodigo(Integer codigoPlataforma);

    /** RETO 3 (alta): ¿existe ya un videojuego con este título (sin distinguir mayúsculas) en esa plataforma? */
    boolean existsByTituloIgnoreCaseAndPlataformaCodigo(String titulo, Integer codigoPlataforma);

    /** RETO 3 (edición): igual que el anterior, pero sin contar al videojuego que se está editando. */
    boolean existsByTituloIgnoreCaseAndPlataformaCodigoAndCodigoNot(String titulo, Integer codigoPlataforma,
                                                                    Integer codigo);
}