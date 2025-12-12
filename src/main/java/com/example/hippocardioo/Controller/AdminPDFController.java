package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.AdminPdfService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/admin/pdf")
public class AdminPDFController {

    private final AdminPdfService adminPdfService;

    public AdminPDFController(AdminPdfService adminPdfService) {
        this.adminPdfService = adminPdfService;
    }

    @GetMapping("/{enfermedad}")
    public ResponseEntity<byte[]> descargarPdfGeneral(@PathVariable String enfermedad) throws IOException {
        // Llamamos al método general que incluye todas las enfermedades
        byte[] pdfBytes = adminPdfService.generarPdfGeneralAdmin();

        // Configurar headers para descarga
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("reporte_general.pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
