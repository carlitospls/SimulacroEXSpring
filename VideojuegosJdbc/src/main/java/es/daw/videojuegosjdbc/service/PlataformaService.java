package es.daw.videojuegosjdbc.service;

import es.daw.videojuegosjdbc.model.Plataforma;
import es.daw.videojuegosjdbc.repository.PlataformaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlataformaService {

    private final PlataformaRepository plataformaRepository;

    public List<Plataforma> listar() {
        return plataformaRepository.findAll();
    }
}
