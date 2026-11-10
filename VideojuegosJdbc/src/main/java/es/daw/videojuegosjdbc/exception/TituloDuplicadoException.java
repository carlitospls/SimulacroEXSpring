package es.daw.videojuegosjdbc.exception;

/**
 * Regla de negocio (RETO 3): no puede haber dos videojuegos con el mismo título en la misma plataforma.
 * La lanza VideojuegoService; la captura VideojuegoController y la muestra bajo el campo "titulo".
 */
public class TituloDuplicadoException extends RuntimeException {

    public TituloDuplicadoException() {
        super("Ya existe un videojuego con ese título en esa plataforma");
    }
}