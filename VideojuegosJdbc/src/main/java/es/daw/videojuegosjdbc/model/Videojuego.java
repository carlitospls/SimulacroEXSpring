package es.daw.videojuegosjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fila de la tabla VIDEOJUEGO.
 * En la tabla hay una columna codigo_plataforma (FK); en Java guardamos el objeto Plataforma completo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    private Integer codigo;
    private String titulo;
    private double precio;
    private Integer stock;
    private Integer pegi;
    private Plataforma plataforma;
}
