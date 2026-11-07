package es.daw.videojuegosjdbc.controller;

import es.daw.videojuegosjdbc.service.PlataformaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plataformas")
@RequiredArgsConstructor
public class PlataformaController {

    private final PlataformaService plataformaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("plataformas", plataformaService.listar());
        return "plataformas/lista";
    }
}
