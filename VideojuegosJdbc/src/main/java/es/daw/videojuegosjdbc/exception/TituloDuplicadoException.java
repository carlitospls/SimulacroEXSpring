package es.daw.videojuegosjdbc.exception;

public class TituloDuplicadoException extends RuntimeException {
    public TituloDuplicadoException(String codigo) {

        super("Ya existe un Videojuego con ese codigo: " + codigo);
    }
}
