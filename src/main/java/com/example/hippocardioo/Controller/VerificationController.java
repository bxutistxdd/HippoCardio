package com.example.hippocardioo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VerificationController {

    @GetMapping("/verify-email")
    public String showVerificationPage(@RequestParam(required = false) String status, Model model) {
        model.addAttribute("status", status); // usado en la plantilla para mostrar el mensaje
        return "verify-email";
    }

    @PostMapping("/verify/resend")
    public String resendVerificationEmail() {
        // Lógica de reenvío (simulada)
        System.out.println("✅ Correo de verificación reenviado.");
        return "redirect:/verify-email?status=verification-link-sent";
    }

    @PostMapping("/logout")
    public String logout() {
        // Por ahora, solo redirige (no implementa autenticación real)
        return "redirect:/login";
    }
}
