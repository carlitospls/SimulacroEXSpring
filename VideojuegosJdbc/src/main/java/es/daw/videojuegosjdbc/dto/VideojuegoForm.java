package es.daw.videojuegosjdbc.dto;

import es.daw.videojuegosjdbc.model.Plataforma;
import es.daw.videojuegosjdbc.model.Videojuego;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto del formulario de videojuego (viaja entre la vista y el controlador).
 * El <select> solo envía el código de la plataforma, por eso aquí es un Integer.
 */
@Data
@NoArgsConstructor
public class VideojuegoForm {

    private Integer codigo;            // null = alta, con valor = edición

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String titulo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;


    @NotNull(message = "Selecciona una plataforma")
    private Integer codigoPlataforma;

    /** Videojuego (modelo) → formulario. Se usa al editar. */
    public static VideojuegoForm toDto(Videojuego v) {
        VideojuegoForm form = new VideojuegoForm();
        form.setCodigo(v.getCodigo());
        form.setTitulo(v.getTitulo());
        form.setPrecio(v.getPrecio());
        form.setCodigoPlataforma(v.getPlataforma().getCodigo());
        return form;
    }

    /** Formulario → Videojuego (modelo). De la plataforma solo tenemos el código. */
    public Videojuego toModelEntity() {
        Plataforma plataforma = new Plataforma(codigoPlataforma, null);
        return new Videojuego(codigo, titulo, precio, stock, plataforma);
    }
}
