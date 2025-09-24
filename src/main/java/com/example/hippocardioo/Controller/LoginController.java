package com.example.hippocardioo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login"; // Carga login.html desde templates
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String remember,
            Model model) {

        // ✅ Caso admin hardcodeado
        if ("admin@hippo.com".equals(email) && "1234".equals(password)) {
            return "/admin/dashboard.html"; 
        }

        // ✅ Caso usuario normal (buscar en la BD)
        Usuario usuario = usuarioService.findByCorreo(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            session.setAttribute("usuarioLogueado", usuario); // Guardar en sesión
            return "redirect:/perfil";
        }

        // ❌ Si nada coincide, volvemos al login con error
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "auth/login";
    }
}
