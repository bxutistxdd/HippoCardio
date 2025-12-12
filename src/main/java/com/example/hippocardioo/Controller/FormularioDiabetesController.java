package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.FormularioDiabetes;
import com.example.hippocardioo.Repository.FormularioDiabetesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/formulario/diabetes")
public class FormularioDiabetesController {

    @Autowired
    private FormularioDiabetesRepository formularioRepo;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("diabetesForm", new FormularioDiabetes());
        return "formularios/diabetes";
    }

    @PostMapping
    public String procesarFormulario(
        @ModelAttribute("diabetesForm") @Valid FormularioDiabetes formulario,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            return "formularios/diabetes";
        }

        formularioRepo.save(formulario);
        model.addAttribute("success", "Formulario enviado correctamente.");
        model.addAttribute("diabetesForm", new FormularioDiabetes()); // limpia el form
        return "formularios/diabetes";
    }
}
