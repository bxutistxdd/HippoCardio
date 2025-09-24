package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Services.DashboardService;
import com.example.hippocardioo.Services.ReportePdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;

@Controller
public class AdminController {

    private final DashboardService dashboardService;
    private final ReportePdfService reportePdfService;

    public AdminController(DashboardService dashboardService, ReportePdfService reportePdfService) {
        this.dashboardService = dashboardService;
        this.reportePdfService = reportePdfService;
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) String tipoDiabetes,
            @RequestParam(required = false) String tipoEts,
            @RequestParam(required = false) String presionArterial,
            @RequestParam(required = false) Double peso,
            @RequestParam(required = false) Double altura,
            @RequestParam(required = false) Double imc,
            Model model) {

        // Llamadas a los métodos del service para filtrar
        model.addAttribute("hipertensionList", dashboardService.filtrarHipertension(nombre, edad, presionArterial, peso, altura));
        model.addAttribute("diabetesList", dashboardService.filtrarDiabetes(nombre, edad, tipoDiabetes));
        model.addAttribute("etsList", dashboardService.filtrarEts(nombre, edad, tipoEts));
        model.addAttribute("obesidadList", dashboardService.filtrarObesidad(nombre, imc));

        // Guardamos también los filtros para mostrarlos en el formulario
        model.addAttribute("nombre", nombre);
        model.addAttribute("edad", edad);
        model.addAttribute("tipoDiabetes", tipoDiabetes);
        model.addAttribute("tipoEts", tipoEts);
        model.addAttribute("presionArterial", presionArterial);
        model.addAttribute("peso", peso);
        model.addAttribute("altura", altura);
        model.addAttribute("imc", imc);

        return "admin/dashboard";
    }

    // ===================== MÉTODO AUXILIAR PDF =====================
    private ResponseEntity<InputStreamResource> crearResponsePdf(ByteArrayInputStream bis, String nombreArchivo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + nombreArchivo);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    // ===================== ENDPOINTS PDF =====================
    @GetMapping("/admin/reporte/diabetes")
    public ResponseEntity<InputStreamResource> descargarReporteDiabetes() {
        ByteArrayInputStream bis = reportePdfService.generarReporteDiabetes();
        return crearResponsePdf(bis, "Reporte_Diabetes.pdf");
    }

    @GetMapping("/admin/reporte/ets")
    public ResponseEntity<InputStreamResource> descargarReporteEts() {
        ByteArrayInputStream bis = reportePdfService.generarReporteEts();
        return crearResponsePdf(bis, "Reporte_ETS.pdf");
    }

    @GetMapping("/admin/reporte/hipertension")
    public ResponseEntity<InputStreamResource> descargarReporteHipertension() {
        ByteArrayInputStream bis = reportePdfService.generarReporteHipertension();
        return crearResponsePdf(bis, "Reporte_Hipertension.pdf");
    }

    @GetMapping("/admin/reporte/obesidad")
    public ResponseEntity<InputStreamResource> descargarReporteObesidad() {
        ByteArrayInputStream bis = reportePdfService.generarReporteObesidad();
        return crearResponsePdf(bis, "Reporte_Obesidad.pdf");
    }
}
