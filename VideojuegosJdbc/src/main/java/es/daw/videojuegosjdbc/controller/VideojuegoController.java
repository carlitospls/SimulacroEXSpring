package es.daw.videojuegosjdbc.controller;

import es.daw.videojuegosjdbc.dto.VideojuegoForm;
import es.daw.videojuegosjdbc.model.Videojuego;
import es.daw.videojuegosjdbc.service.PlataformaService;
import es.daw.videojuegosjdbc.service.VideojuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// URLs: GET /videojuegos | GET /videojuegos/nuevo | GET /videojuegos/editar/{codigo}
//       POST /videojuegos/guardar | POST /videojuegos/borrar/{codigo}
@Controller
@RequestMapping("/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoService videojuegoService;
    private final PlataformaService plataformaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("videojuegos", videojuegoService.listar());
        return "videojuegos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("videojuegoForm", new VideojuegoForm());
        model.addAttribute("plataformas", plataformaService.listar());
        return "videojuegos/formulario";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Integer codigo, Model model) {
        Videojuego videojuego = videojuegoService.buscarPorCodigo(codigo);
        model.addAttribute("videojuegoForm", VideojuegoForm.toDto(videojuego));
        model.addAttribute("plataformas", plataformaService.listar());
        return "videojuegos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("videojuegoForm") VideojuegoForm videojuegoForm,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("plataformas", plataformaService.listar());
            return "videojuegos/formulario";
        }

        if (videojuegoForm.getCodigo() == null) {
            Videojuego creado = videojuegoService.crear(videojuegoForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Videojuego creado con código " + creado.getCodigo());
        } else {
            videojuegoService.actualizar(videojuegoForm.toModelEntity());
            redirectAttributes.addFlashAttribute("mensaje",
                    "Videojuego " + videojuegoForm.getCodigo() + " actualizado");
        }
        return "redirect:/videojuegos";
    }

    @PostMapping("/borrar/{codigo}")
    public String borrar(@PathVariable Integer codigo, RedirectAttributes redirectAttributes) {
        videojuegoService.borrar(codigo);
        redirectAttributes.addFlashAttribute("mensaje", "Videojuego " + codigo + " borrado");
        return "redirect:/videojuegos";
    }
}
