package es.daw.videojuegosjdbc.exception;

/** Error técnico al acceder a la BD. Envuelve la SQLException original. */
public class AccesoDatosException extends RuntimeException {

    public AccesoDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
