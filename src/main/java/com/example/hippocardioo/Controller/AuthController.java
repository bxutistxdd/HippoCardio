package com.example.hippocardioo.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @GetMapping("/confirm-password")
    public String showConfirmPasswordForm(Model model) {
        model.addAttribute("password", ""); // Para usar con Thymeleaf si lo necesitas
        return "auth/confirm-password";
    }

    @PostMapping("/confirm-password")
    public String confirmPassword(@RequestParam("password") String password, HttpSession session, Model model) {
        String storedPassword = "123456"; //un ejemploo

        if (password.equals(storedPassword)) {
            // Redirige a la zona segura
            return "redirect:/perfil";
        } else {
            model.addAttribute("error", "Contraseña incorrecta.");
            return "auth/confirm-password";
        }
    }
}
