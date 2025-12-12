package com.example.hippocardioo.Services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Transactional
public class PdfService {

    public byte[] generarPdfUsuario(String nombreEnfermedad) throws IOException, DocumentException {

        String descripcion = getDescripcionExtendida(nombreEnfermedad);
        List<String> causas = getCausasPorEnfermedad(nombreEnfermedad);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 55, 55, 55, 55);
        PdfWriter.getInstance(document, baos);

        document.open();

        // ----------------------------------------------------------
        // LOGO PRINCIPAL
        // ----------------------------------------------------------
        try {
            Image logo = Image.getInstance(
                    new ClassPathResource("static/images/Hippocardio.png").getURL()
            );
            logo.scaleToFit(120, 120);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.setSpacingAfter(20);
            document.add(logo);
        } catch (Exception ignored) {}

        // ----------------------------------------------------------
        // TÍTULO
        // ----------------------------------------------------------
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, new Color(30, 30, 30));
        Paragraph titulo = new Paragraph(nombreEnfermedad.toUpperCase(), titleFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(10);
        document.add(titulo);

        agregarLineaDecorativa(document);

        // ----------------------------------------------------------
        // SUBTÍTULO
        // ----------------------------------------------------------
        Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 13, new Color(90, 90, 90));
        Paragraph subtitulo = new Paragraph("Descripción detallada", subtitleFont);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(15);
        document.add(subtitulo);

        // Fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Font fechaFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 11, new Color(120, 120, 120));
        Paragraph fecha = new Paragraph(
                "Exportado el: " + LocalDateTime.now().format(formatter), fechaFont
        );
        fecha.setAlignment(Element.ALIGN_RIGHT);
        fecha.setSpacingAfter(20);
        document.add(fecha);

        // ----------------------------------------------------------
        // DESCRIPCIÓN EXTENDIDA
        // ----------------------------------------------------------
        Font descFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new Color(40, 40, 40));
        Paragraph desc = new Paragraph(descripcion, descFont);
        desc.setAlignment(Element.ALIGN_JUSTIFIED);
        desc.setSpacingAfter(30);
        document.add(desc);

        // ----------------------------------------------------------
        // SALTO DE PÁGINA
        // ----------------------------------------------------------
        document.newPage();

        // ----------------------------------------------------------
        // LOGO PEQUEÑO EN PÁGINA 2
        // ----------------------------------------------------------
        try {
            Image logoSmall = Image.getInstance(
                    new ClassPathResource("static/images/Hippocardio.png").getURL()
            );
            logoSmall.scaleToFit(80, 80);
            logoSmall.setAlignment(Element.ALIGN_RIGHT);
            logoSmall.setSpacingAfter(10);
            document.add(logoSmall);
        } catch (Exception ignored) {}

        // ----------------------------------------------------------
        // TÍTULO CAUSAS
        // ----------------------------------------------------------
        Font causasTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, new Color(40, 40, 40));
        Paragraph causasTitle = new Paragraph("Causas de " + nombreEnfermedad, causasTitleFont);
        causasTitle.setSpacingAfter(15);
        document.add(causasTitle);

        agregarLineaDecorativa(document);

        // ----------------------------------------------------------
        // LISTA DE CAUSAS
        // ----------------------------------------------------------
        Font causaFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new Color(50, 50, 50));

        for (String c : causas) {
            Paragraph item = new Paragraph("• " + c, causaFont);
            item.setSpacingAfter(8);
            document.add(item);
        }

        document.close();
        return baos.toByteArray();
    }


    // ----------------------------------------------------------
    // LÍNEA DECORATIVA SIN LineSeparator
    // ----------------------------------------------------------
    private void agregarLineaDecorativa(Document doc) throws DocumentException {

        Table t = new Table(1);
        t.setWidth(100);
        t.getDefaultCell().setBorderWidth(0);
        t.getDefaultCell().setBackgroundColor(new Color(200, 40, 40)); // Rojo corporativo
        t.setPadding(1);
        t.setSpacing(8);

        t.addCell(" ");
        doc.add(t);
    }

    // ----------------------------------------------------------
    // DESCRIPCIONES EXTENDIDAS COMPLETAS
    // ----------------------------------------------------------
    public String getDescripcionExtendida(String enfermedad) {

        switch (enfermedad.toLowerCase()) {

            case "hipertension":
                return """
La hipertensión arterial es una condición crónica en la cual la presión con la que la sangre circula a través de las arterias se mantiene elevada durante largos periodos. 
Este aumento de presión obliga al corazón a trabajar con mayor fuerza para bombear sangre, lo que puede generar un desgaste progresivo del sistema cardiovascular. 
Frecuentemente se desarrolla de manera silenciosa, sin síntomas evidentes, lo que la convierte en un riesgo significativo para la salud. 
Cuando no se controla adecuadamente, puede conducir a enfermedades coronarias, accidentes cerebrovasculares, insuficiencia renal, pérdida de visión y otros problemas graves.
La predisposición genética, el consumo excesivo de sal, el estrés persistente, el sedentarismo y el sobrepeso son factores que aumentan la probabilidad de desarrollar esta condición. 
El diagnóstico temprano y el control adecuado son claves para reducir complicaciones y proteger la salud a largo plazo.
""";

            case "diabetes":
                return """
La diabetes mellitus es un trastorno metabólico crónico caracterizado por niveles elevados de glucosa en la sangre debido a fallos en la producción o en la acción de la insulina. 
Esta hormona permite que la glucosa entre a las células, donde se utiliza como fuente de energía. Cuando el proceso falla, la glucosa se acumula en la sangre 
y comienza a afectar diversos órganos. La diabetes puede dañar la visión, el sistema nervioso, los riñones, el corazón y la circulación periférica.
El desarrollo de la enfermedad está influenciado por factores como genética, obesidad, inactividad física y hábitos alimentarios deficientes. 
Un tratamiento adecuado implica una combinación de dieta equilibrada, actividad física, medicación cuando es necesaria y monitoreo frecuente para evitar complicaciones graves.
""";

            case "obesidad":
                return """
La obesidad es una condición caracterizada por un exceso de grasa corporal que afecta la salud física y metabólica. No se trata únicamente de un tema estético, 
sino de un riesgo que se asocia a enfermedades como hipertensión, diabetes tipo 2, apnea del sueño, problemas articulares y patologías cardiovasculares. 
Su origen suele ser multifactorial: hábitos alimenticios inadecuados, sedentarismo, predisposición genética o alteraciones hormonales.
Para su tratamiento se requiere un enfoque integral que combine cambios en la alimentación, aumento de la actividad física, apoyo emocional 
y en algunos casos intervenciones médicas o quirúrgicas. La obesidad es considerada un problema de salud pública que requiere atención constante.
""";

            case "ets":
                return """
Las enfermedades de transmisión sexual son infecciones que se propagan principalmente mediante el contacto sexual. 
Pueden transmitirse a través de relaciones vaginales, anales u orales, así como por contacto con fluidos corporales contaminados. 
Algunas ETS pueden no mostrar síntomas durante largos periodos, facilitando su transmisión sin que la persona afectada lo sepa. 
Cuando no se tratan adecuadamente, pueden generar complicaciones como infertilidad, afectaciones neurológicas, transmisión crónica o incluso cáncer.
La prevención, el uso de protección, las pruebas periódicas y el tratamiento oportuno son fundamentales para disminuir su propagación.
""";

            default:
                return "Información no disponible.";
        }
    }

    // ----------------------------------------------------------
    // CAUSAS
    // ----------------------------------------------------------
    public List<String> getCausasPorEnfermedad(String enfermedad) {

        return switch (enfermedad.toLowerCase()) {

            case "hipertension" -> List.of(
                "Consumo excesivo de sal",
                "Sedentarismo",
                "Estrés prolongado",
                "Predisposición genética",
                "Obesidad o sobrepeso",
                "Consumo frecuente de alcohol"
            );

            case "diabetes" -> List.of(
                "Genética",
                "Resistencia a la insulina",
                "Sobrepeso y obesidad",
                "Dieta rica en azúcares simples",
                "Estilo de vida sedentario"
            );

            case "obesidad" -> List.of(
                "Consumo excesivo de calorías",
                "Falta de actividad física",
                "Factores genéticos",
                "Desequilibrios hormonales",
                "Mala calidad del sueño"
            );

            case "ets" -> List.of(
                "Relaciones sexuales sin protección",
                "Exposición a fluidos corporales infectados",
                "Múltiples parejas sexuales",
                "Falta de pruebas médicas periódicas"
            );

            default -> List.of("Causas no registradas.");
        };
    }

    public BufferedImage crearGraficoPorEnfermedad(String enfermedad) {
        return null;
    }
}
