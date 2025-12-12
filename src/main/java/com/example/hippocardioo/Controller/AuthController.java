package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Services.EmailService;
import com.example.hippocardioo.Services.TokenService;
import com.example.hippocardioo.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    // ----------------------------------------------------
    // CONFIRMAR CONTRASEÑA
    // ----------------------------------------------------
    @GetMapping("/confirm-password")
    public String showConfirmPasswordForm(Model model) {
        model.addAttribute("password", "");
        return "auth/confirm-password";
    }

    @PostMapping("/confirm-password")
    public String confirmPassword(@RequestParam("password") String password, HttpSession session, Model model) {
        String storedPassword = "123456"; // Temporal

        if (password.equals(storedPassword)) {
            return "redirect:/perfil";
        } else {
            model.addAttribute("error", "Contraseña incorrecta.");
            return "auth/confirm-password";
        }
    }

    // ----------------------------------------------------
    // RECUPERAR CONTRASEÑA (Solicitar link)
    // ----------------------------------------------------
    @GetMapping("/recuperar-password")
    public String recuperarPasswordForm() {
        return "auth/recuperar-password";
    }

    @PostMapping("/recuperar-password")
    public String procesarRecuperacion(@RequestParam("email") String email, Model model) {

        String token = tokenService.generarToken(email);

        String linkRecuperacion = "http://localhost:8081/reset-password?token=" + token;

        String mensaje = "Hola,\n\n" +
                "Recibimos una solicitud para restablecer la contraseña de tu cuenta.\n" +
                "Haz clic en el siguiente enlace para continuar:\n\n" +
                linkRecuperacion + "\n\n" +
                "Si no solicitaste esto, simplemente ignora este mensaje.\n\n" +
                "Hippocardio";

        emailService.enviarCorreo(email, "Recuperación de contraseña", mensaje);

        model.addAttribute("mensaje", "Si tu correo existe, enviaremos un enlace de recuperación.");
        return "auth/recuperar-password";
    }

    // ----------------------------------------------------
    // RESET PASSWORD (Vista del formulario)
    // ----------------------------------------------------
    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, Model model) {

        String email = tokenService.obtenerEmailPorToken(token);

        if (email == null) {
            model.addAttribute("error", "Enlace inválido o expirado.");
            return "auth/reset-password-error";
        }

        model.addAttribute("email", email);
        model.addAttribute("token", token);
        return "auth/reset-password";
    }

    // ----------------------------------------------------
    // PROCESAR NUEVA CONTRASEÑA
    // ----------------------------------------------------
    @PostMapping("/reset-password")
    public String procesarNuevoPassword(
            @RequestParam("token") String token,
            @RequestParam("password") String password,
            Model model) {

        String email = tokenService.obtenerEmailPorToken(token);

        if (email == null) {
            model.addAttribute("error", "El enlace ya no es válido.");
            return "auth/reset-password-error";
        }

        Usuario usuario = usuarioService.findByCorreo(email);

        if (usuario == null) {
            model.addAttribute("error", "El usuario no existe.");
            return "auth/reset-password-error";
        }

        // ---------------------------
        // ENCRIPTAR SOLO AQUÍ
        // ---------------------------
        usuarioService.actualizarPassword(usuario.getCorreo(), password);

        tokenService.eliminarToken(token);

        return "auth/reset-password-exito";
    }
}
