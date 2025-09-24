package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.ReportePdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/reportes")
public class ReportePdfController {

    private final ReportePdfService reportePdfService;

    public ReportePdfController(ReportePdfService reportePdfService) {
        this.reportePdfService = reportePdfService;
    }

    @GetMapping("/diabetes")
    public ResponseEntity<InputStreamResource> descargarReporteDiabetes() {
        ByteArrayInputStream pdf = reportePdfService.generarReporteDiabetes();
        return construirRespuestaPdf(pdf, "reporte_diabetes.pdf");
    }

    @GetMapping("/ets")
    public ResponseEntity<InputStreamResource> descargarReporteEts() {
        ByteArrayInputStream pdf = reportePdfService.generarReporteEts();
        return construirRespuestaPdf(pdf, "reporte_ets.pdf");
    }

    @GetMapping("/hipertension")
    public ResponseEntity<InputStreamResource> descargarReporteHipertension() {
        ByteArrayInputStream pdf = reportePdfService.generarReporteHipertension();
        return construirRespuestaPdf(pdf, "reporte_hipertension.pdf");
    }

    @GetMapping("/obesidad")
    public ResponseEntity<InputStreamResource> descargarReporteObesidad() {
        ByteArrayInputStream pdf = reportePdfService.generarReporteObesidad();
        return construirRespuestaPdf(pdf, "reporte_obesidad.pdf");
    }

    // Método utilitario para construir respuesta PDF
    private ResponseEntity<InputStreamResource> construirRespuestaPdf(ByteArrayInputStream pdf, String nombreArchivo) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
