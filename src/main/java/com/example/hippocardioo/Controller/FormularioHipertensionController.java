package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.FormularioHipertension;
import com.example.hippocardioo.Repository.FormularioHipertensionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/formulario/hipertension")
public class FormularioHipertensionController {

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("hipertensionForm", new FormularioHipertension());
        return "formularios/hipertension";  // nombre del archivo HTML en templates/hipertension.html
    }

    @PostMapping
    public String procesarFormulario(
            @ModelAttribute("hipertensionForm") @Valid FormularioHipertension formularioHipertension,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "formularios/hipertension";
        }

        formularioHipertensionRepository.save(formularioHipertension);

        model.addAttribute("success", "Formulario enviado correctamente");
        model.addAttribute("hipertensionForm", new FormularioHipertension()); // Limpiar formulario
        return "formularios/hipertension";
    }

    @Autowired
    private FormularioHipertensionRepository formularioHipertensionRepository;
}
