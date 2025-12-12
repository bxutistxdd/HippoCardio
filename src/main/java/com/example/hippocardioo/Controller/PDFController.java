package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.PdfService;
import com.lowagie.text.DocumentException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    private final PdfService pdfService;

    public PDFController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    /**
     * Endpoint para descargar un PDF informativo de una enfermedad
     * @param enfermedad Nombre de la enfermedad (ej. hipertension)
     * @return PDF generado como attachment
     * @throws IOException
     * @throws DocumentException
     */
    @GetMapping("/{enfermedad}")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable String enfermedad) throws IOException, DocumentException {
        // Generar PDF usando el nuevo servicio para usuarios
        byte[] pdfBytes = pdfService.generarPdfUsuario(enfermedad);

        // Configurar headers para descarga
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(enfermedad + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
