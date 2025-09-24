package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.CorreoService;
import com.example.hippocardioo.Dto.CorreoRequest;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correos")
public class CorreoController {

    private final CorreoService correoService;

    public CorreoController(CorreoService correoService) {
        this.correoService = correoService;
    }

    // 1. Enviar correo masivo (uno a uno)
    @PostMapping("/masivo")
    public ResponseEntity<String> enviarMasivo(@RequestBody CorreoRequest request) {
        correoService.enviarCorreoMasivo(request.getDestinatarios(), request.getAsunto(), request.getContenido());
        return ResponseEntity.ok("Correos enviados individualmente");
    }

    // 2. Enviar correo usando BCC
    @PostMapping("/bcc")
    public ResponseEntity<String> enviarBCC(@RequestBody CorreoRequest request) {
        correoService.enviarCorreoBCC(request.getDestinatarios(), request.getAsunto(), request.getContenido());
        return ResponseEntity.ok("Correos enviados con BCC");
    }

    // 3. Enviar correo con HTML
    @PostMapping("/html")
    public ResponseEntity<String> enviarHTML(@RequestBody CorreoRequest request) throws MessagingException {
        correoService.enviarCorreoHTML(request.getDestinatarios(), request.getAsunto(), request.getContenido());
        return ResponseEntity.ok("Correos HTML enviados");
    }
}
