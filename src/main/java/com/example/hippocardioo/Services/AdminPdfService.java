package com.example.hippocardioo.Services;

import com.example.hippocardioo.Entity.*;
import com.example.hippocardioo.Repository.*;
import com.lowagie.text.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class AdminPdfService {

    private final FormularioDiabetesRepository diabetesRepo;
    private final FormularioEtsRepository etsRepo;
    private final FormularioHipertensionRepository hipertensionRepo;
    private final FormularioObesidadRepository obesidadRepo;

    public AdminPdfService(FormularioDiabetesRepository diabetesRepo,
                           FormularioEtsRepository etsRepo,
                           FormularioHipertensionRepository hipertensionRepo,
                           FormularioObesidadRepository obesidadRepo) {
        this.diabetesRepo = diabetesRepo;
        this.etsRepo = etsRepo;
        this.hipertensionRepo = hipertensionRepo;
        this.obesidadRepo = obesidadRepo;
    }

    public byte[] generarPdfGeneralAdmin() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 60, 60, 60, 60);

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Logo Hippocardio
            try {
                Image logo = Image.getInstance(getClass().getResource("/static/images/Hippocardio.png"));
                logo.scaleToFit(100, 100);
                logo.setAlignment(Element.ALIGN_CENTER);
                logo.setSpacingAfter(20);
                document.add(logo);
            } catch (Exception e) {}

            // Título principal
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new Color(30,30,30));
            Paragraph titulo = new Paragraph("Reporte General de Formularios", titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(10);
            document.add(titulo);

            // Subtítulo discreto
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new Color(80,80,80));
            Paragraph subtitulo = new Paragraph(
                    "Resumen de usuarios por enfermedad y total de registros",
                    subtitleFont
            );
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            document.add(subtitulo);

            // Fecha de exportación
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Paragraph fechaPara = new Paragraph(
                    "Fecha de exportación: " + LocalDateTime.now().format(formatter),
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, Color.GRAY)
            );
            fechaPara.setAlignment(Element.ALIGN_RIGHT);
            fechaPara.setSpacingAfter(20);
            document.add(fechaPara);

            // Tabla resumen
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(50);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.setSpacingBefore(20);
            tabla.setSpacingAfter(25);
            tabla.setWidths(new int[]{3, 2});

            PdfPCell celdaEncabezado1 = new PdfPCell(new Phrase("Enfermedad", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE)));
            celdaEncabezado1.setBackgroundColor(new Color(52,152,219));
            celdaEncabezado1.setPadding(8);
            celdaEncabezado1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celdaEncabezado1);

            PdfPCell celdaEncabezado2 = new PdfPCell(new Phrase("Usuarios Totales", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE)));
            celdaEncabezado2.setBackgroundColor(new Color(52,152,219));
            celdaEncabezado2.setPadding(8);
            celdaEncabezado2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celdaEncabezado2);

            // Datos de cada enfermedad
            addFilaTabla(tabla, "Diabetes", diabetesRepo.findAll().size());
            addFilaTabla(tabla, "ETS", etsRepo.findAll().size());
            addFilaTabla(tabla, "Hipertensión", hipertensionRepo.findAll().size());
            addFilaTabla(tabla, "Obesidad", obesidadRepo.findAll().size());

            document.add(tabla);

            // Gráfico general con colores distintos
            BufferedImage chart = crearGraficoGeneral(
                    diabetesRepo.findAll().size(),
                    etsRepo.findAll().size(),
                    hipertensionRepo.findAll().size(),
                    obesidadRepo.findAll().size()
            );
            if (chart != null) {
                ByteArrayOutputStream chartOs = new ByteArrayOutputStream();
                ImageIO.write(chart, "png", chartOs);
                Image img = Image.getInstance(chartOs.toByteArray());
                img.scaleToFit(500, 300);
                img.setAlignment(Element.ALIGN_CENTER);
                img.setSpacingBefore(10);
                img.setSpacingAfter(25);
                document.add(img);
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    private void addFilaTabla(PdfPTable tabla, String nombre, int total) {
        PdfPCell celda1 = new PdfPCell(new Phrase(nombre, FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK)));
        celda1.setPadding(6);
        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda1);

        PdfPCell celda2 = new PdfPCell(new Phrase(String.valueOf(total), FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK)));
        celda2.setPadding(6);
        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda2);
    }

    private BufferedImage crearGraficoGeneral(int diabetes, int ets, int hipertension, int obesidad) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Cada barra es su propia serie para poder asignarle un color único
        dataset.addValue(diabetes, "Diabetes", "Usuarios");
        dataset.addValue(ets, "ETS", "Usuarios");
        dataset.addValue(hipertension, "Hipertensión", "Usuarios");
        dataset.addValue(obesidad, "Obesidad", "Usuarios");

        JFreeChart chart = ChartFactory.createBarChart(
                "Usuarios por enfermedad",
                "Enfermedad",
                "Cantidad",
                dataset
        );

        // Cambiar colores por serie
        org.jfree.chart.renderer.category.BarRenderer renderer =
                (org.jfree.chart.renderer.category.BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new Color(52,152,219)); // azul
        renderer.setSeriesPaint(1, new Color(46,204,113));  // verde
        renderer.setSeriesPaint(2, new Color(241,196,15));  // amarillo
        renderer.setSeriesPaint(3, new Color(155,89,182));  // morado
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
        renderer.setShadowVisible(false);

        // Apariencia del gráfico
        chart.setBackgroundPaint(Color.WHITE);
        chart.getCategoryPlot().setBackgroundPaint(Color.WHITE);
        chart.getCategoryPlot().setRangeGridlinePaint(new Color(200,200,200));
        chart.getCategoryPlot().setOutlineVisible(false);

        return chart.createBufferedImage(500, 300);
    }

}
