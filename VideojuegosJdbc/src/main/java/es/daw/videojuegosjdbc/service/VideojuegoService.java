package es.daw.videojuegosjdbc.service;

import es.daw.videojuegosjdbc.exception.RecursoNoEncontradoException;
import es.daw.videojuegosjdbc.model.Plataforma;
import es.daw.videojuegosjdbc.model.Videojuego;
import es.daw.videojuegosjdbc.repository.PlataformaRepository;
import es.daw.videojuegosjdbc.repository.VideojuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Capa de lógica: reglas de negocio. No sabe nada de HTTP ni de SQL.
@Service
@RequiredArgsConstructor
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;
    private final PlataformaRepository plataformaRepository;

    public List<Videojuego> listar() {
        
        return videojuegoRepository.findAll();
    }

    public Videojuego buscarPorCodigo(Integer codigo) {
        return videojuegoRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el videojuego con código " + codigo));
    }

    public Videojuego crear(Videojuego videojuego) {
        prepararParaGuardar(videojuego);
        videojuegoRepository.save(videojuego);
        return videojuego;
    }

    public Videojuego actualizar(Videojuego videojuego) {
        buscarPorCodigo(videojuego.getCodigo());
        prepararParaGuardar(videojuego);
        videojuegoRepository.update(videojuego);
        return videojuego;
    }

    public void borrar(Integer codigo) {
        buscarPorCodigo(codigo);
        videojuegoRepository.delete(codigo);
    }

    // Reglas comunes a crear y actualizar
    private void prepararParaGuardar(Videojuego videojuego) {
        videojuego.setTitulo(videojuego.getTitulo().trim());

        Integer codigoPlataforma = videojuego.getPlataforma().getCodigo();
        Plataforma plataforma = plataformaRepository.findById(codigoPlataforma)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la plataforma con código " + codigoPlataforma));
        videojuego.setPlataforma(plataforma);
    }
}
