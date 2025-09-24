package com.example.hippocardioo.Services;

import com.example.hippocardioo.Entity.*;
import com.example.hippocardioo.Repository.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportePdfService {

    private final FormularioDiabetesRepository diabetesRepo;
    private final FormularioEtsRepository etsRepo;
    private final FormularioHipertensionRepository hipertensionRepo;
    private final FormularioObesidadRepository obesidadRepo;

    public ReportePdfService(
            FormularioDiabetesRepository diabetesRepo,
            FormularioEtsRepository etsRepo,
            FormularioHipertensionRepository hipertensionRepo,
            FormularioObesidadRepository obesidadRepo
    ) {
        this.diabetesRepo = diabetesRepo;
        this.etsRepo = etsRepo;
        this.hipertensionRepo = hipertensionRepo;
        this.obesidadRepo = obesidadRepo;
    }

    // ===================== DIABETES =====================
    public ByteArrayInputStream generarReporteDiabetes() {
        List<FormularioDiabetes> datos = diabetesRepo.findAll();

        Map<String, Long> conteoPorTipo = datos.stream()
                .collect(Collectors.groupingBy(FormularioDiabetes::getTipoDiabetes, Collectors.counting()));

        double promedioEdad = datos.stream().mapToInt(FormularioDiabetes::getEdad).average().orElse(0.0);

        return generarPdf("Formulario Diabetes", conteoPorTipo, promedioEdad, "Tipo de Diabetes");
    }

    // ===================== ETS =====================
    public ByteArrayInputStream generarReporteEts() {
        List<FormularioEts> datos = etsRepo.findAll();

        Map<String, Long> conteoPorTipo = datos.stream()
                .collect(Collectors.groupingBy(FormularioEts::getTipoEts, Collectors.counting()));

        double promedioEdad = datos.stream().mapToInt(FormularioEts::getEdad).average().orElse(0.0);

        return generarPdf("Formulario ETS", conteoPorTipo, promedioEdad, "Tipo de ETS");
    }

    // ===================== HIPERTENSIÓN =====================
    public ByteArrayInputStream generarReporteHipertension() {
        List<FormularioHipertension> datos = hipertensionRepo.findAll();

        Map<String, Long> conteoPorPresion = datos.stream()
                .collect(Collectors.groupingBy(FormularioHipertension::getPresionArterial, Collectors.counting()));

        double promedioEdad = datos.stream().mapToInt(FormularioHipertension::getEdad).average().orElse(0.0);

        return generarPdf("Formulario Hipertensión", conteoPorPresion, promedioEdad, "Presión Arterial");
    }

    // ===================== OBESIDAD =====================
    public ByteArrayInputStream generarReporteObesidad() {
        List<FormularioObesidad> datos = obesidadRepo.findAll();

        double promedioEdad = datos.stream().mapToInt(FormularioObesidad::getEdad).average().orElse(0.0);
        double promedioImc = datos.stream().mapToDouble(FormularioObesidad::getImc).average().orElse(0.0);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph titulo = new Paragraph("Reporte Estadístico - Formulario Obesidad", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            document.add(new Paragraph("Promedio de Edad: " + String.format("%.2f", promedioEdad), normalFont));
            document.add(new Paragraph("Promedio de IMC: " + String.format("%.2f", promedioImc), normalFont));

            document.close();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF Obesidad: " + e.getMessage());
        }
    }

    // ===================== UTILIDAD PARA CREAR PDF =====================
    private ByteArrayInputStream generarPdf(String tituloReporte, Map<String, Long> conteo, double promedio, String columna) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph titulo = new Paragraph("Reporte Estadístico - " + tituloReporte, tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(60);
            tabla.setWidths(new int[]{3, 2});

            tabla.addCell(new PdfPCell(new Phrase(columna, headerFont)));
            tabla.addCell(new PdfPCell(new Phrase("Cantidad", headerFont)));

            for (Map.Entry<String, Long> entry : conteo.entrySet()) {
                tabla.addCell(new PdfPCell(new Phrase(entry.getKey(), normalFont)));
                tabla.addCell(new PdfPCell(new Phrase(entry.getValue().toString(), normalFont)));
            }

            document.add(tabla);

            Paragraph promedioEdad = new Paragraph("Promedio de Edad: " + String.format("%.2f", promedio), normalFont);
            promedioEdad.setSpacingBefore(20);
            document.add(promedioEdad);

            document.close();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF: " + e.getMessage());
        }
    }
}
