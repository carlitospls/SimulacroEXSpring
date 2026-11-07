package es.daw.videojuegosjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Fila de la tabla PLATAFORMA. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma {

    private Integer codigo;
    private String nombre;
}
