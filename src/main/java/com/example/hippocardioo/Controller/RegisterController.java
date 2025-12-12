package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class RegisterController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Mostrar formulario de registro
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new Usuario()); // Para el form
        return "auth/register"; // register.html
    }

    // Procesar registro
    @PostMapping("/register")
    public String processRegister(
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password_confirmation,
            Model model
    ) {
        // 1. Validar contraseñas
        if (!password.equals(password_confirmation)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "auth/register";
        }

        // 2. Validar que no exista el correo
        Usuario existingUser = usuarioRepository.findByCorreo(email);
        if (existingUser != null) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "auth/register";
        }

        // 3. Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(name);
        usuario.setApellido(lastname);
        usuario.setCorreo(email);

        // ✅ Encriptar la contraseña antes de guardar
        String passEncriptada = passwordEncoder.encode(password);
        usuario.setPassword(passEncriptada);

        usuario.setRol("USER"); // Rol por defecto

        // 4. Guardar en BD
        usuarioRepository.save(usuario);

        // 5. Redirigir al login
        return "redirect:/login";
    }
}
