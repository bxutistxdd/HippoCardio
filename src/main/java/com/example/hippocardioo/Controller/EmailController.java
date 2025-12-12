package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.EmailMasivosService;
import com.example.hippocardioo.Dto.EmailMasivoRequest;
import com.example.hippocardioo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailMasivosService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para obtener correos de la DB

    public EmailController(EmailMasivosService emailService) {
        this.emailService = emailService;
    }

    // Endpoint de prueba individual
    @GetMapping("/test")
    public String testEmail() {
        emailService.sendEmail(
                "byecid3178eg@gmail.com",
                "Prueba de envío masivo",
                "Mensaje enviado desde Hippocardio porque al parecer todo hay que hacerlo en modo contrarreloj."
        );
        return "Correo enviado";
    }

    // Endpoint para enviar correos masivos desde el front
    @PostMapping("/masivo")
    public ResponseEntity<String> enviarMasivo(@RequestBody EmailMasivoRequest req) {
        try {
            emailService.sendEmails(req.getDestinatarios(), req.getAsunto(), req.getMensaje());
            return ResponseEntity.ok("Correos enviados");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar correos: " + e.getMessage());
        }
    }

    // Endpoint opcional: devuelve correos de todos los usuarios activos
    @GetMapping("/usuarios/emails")
    public List<String> obtenerEmails() {
        return usuarioRepository.findAllEmails(); // Suponiendo que este método existe
    }
}
