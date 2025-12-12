package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.CorreoService;
import com.example.hippocardioo.Dto.CorreoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/correos")
public class CorreoController {

    private final CorreoService correoService;

    public CorreoController(CorreoService correoService) {
        this.correoService = correoService;
    }

    // Nuevo endpoint para envío masivo y personalizado usando SendGrid
    @PostMapping("/personalizado-sendgrid")
    public ResponseEntity<String> enviarPersonalizadoSendGrid(@RequestBody CorreoRequest request) {
        
        if (request.getDestinatarios() == null || request.getDestinatarios().isEmpty()) {
            return ResponseEntity.badRequest().body("La lista de destinatarios no puede estar vacía.");
        }

        try {
            correoService.enviarCorreoPersonalizadoSendGrid(
                request.getDestinatarios(), 
                request.getAsunto(), 
                request.getContenidoHTML()
            );
            return ResponseEntity.ok("Correos personalizados enviados mediante SendGrid.");
        } catch (IOException e) {
            // Un error de SendGrid generalmente se reporta como una IOException
            System.err.println("Error al enviar correos: " + e.getMessage());
            return ResponseEntity.status(500).body("Error interno al enviar correos: " + e.getMessage());
        }
    }
}