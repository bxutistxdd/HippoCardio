package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.FormularioEts;
import com.example.hippocardioo.Repository.FormularioEtsRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/formularios/ets")
public class FormularioEtsController {

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("etsForm", new FormularioEts());
        return "formularios/ets";
    }

    @PostMapping
    public String procesarFormulario(
            @ModelAttribute("etsForm") @Valid FormularioEts formularioEts,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "formularios/ets";
        }

        formularioEtsRepository.save(formularioEts);

        model.addAttribute("success", "Formulario enviado correctamente");
        model.addAttribute("etsForm", new FormularioEts()); // limpia el form
        return "formularios/ets";
    }

    @Autowired
    private FormularioEtsRepository formularioEtsRepository;

}
