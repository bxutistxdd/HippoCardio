package com.example.hippocardioo.Controller;
import com.example.hippocardioo.Entity.FormularioObesidad;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.hippocardioo.Repository.FormularioObesidadRepository;


@Controller
@RequestMapping("/formularios/obesidad")
public class FormularioObesidadController {

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("obesidadForm", new FormularioObesidad());
        return "formularios/obesidad"; // buscará templates/formularios/obesidad.html
    }

    @PostMapping
    public String procesarFormulario(
            @ModelAttribute("obesidadForm") @Valid FormularioObesidad formularioObesidad,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "formularios/obesidad";
        }

        formularioObesidadRepository.save(formularioObesidad);

        model.addAttribute("success", "Formulario enviado correctamente");
        model.addAttribute("obesidadForm", new FormularioObesidad());
        return "formularios/obesidad";
    }

    @Autowired
    private FormularioObesidadRepository formularioObesidadRepository;
}
