package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @GetMapping
    public String showPerfil(HttpSession session, Model model) {
        // Sacamos el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            // Si no hay usuario en sesión, redirigimos al login
            return "redirect:/login";
        }

        // Pasamos el usuario a la vista
        model.addAttribute("usuario", usuario);

        return "perfil"; // Renderiza templates/perfil.html
    }
}
