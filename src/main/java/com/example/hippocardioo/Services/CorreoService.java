package com.example.hippocardioo.Services;

// Importaciones de SendGrid
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
// Importación del DTO
import com.example.hippocardioo.Dto.DestinatarioPersonalizado; 

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CorreoService {

    // **NOTA:** La clave se obtiene de la variable de entorno SENDGRID_API_KEY
    private final SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    private final Email fromEmail = new Email("remitente@tudominio.com"); // Reemplaza con tu correo verificado

    public CorreoService() {
        // Se recomienda verificar que la clave exista al iniciar el servicio
        if (System.getenv("SENDGRID_API_KEY") == null) {
            System.err.println("ERROR: La variable de entorno SENDGRID_API_KEY NO está configurada.");
        }
    }
    
    /**
     * Envía correos masivos con personalización usando la API de SendGrid.
     * Genera una sola llamada a la API con múltiples objetos Personalization.
     */
    public void enviarCorreoPersonalizadoSendGrid(
            List<DestinatarioPersonalizado> listaDestinatarios, 
            String asunto, 
            String htmlPlantilla) throws IOException {

        Mail mail = new Mail();
        mail.setFrom(fromEmail);
        mail.setSubject(asunto);
        // Usa el contenido HTML base que contiene los marcadores {{...}}
        mail.addContent(new Content("text/html", htmlPlantilla)); 

        // Itera sobre los destinatarios y crea un objeto Personalization para cada uno
        for (DestinatarioPersonalizado destinatario : listaDestinatarios) {
            Personalization personalization = new Personalization();
            personalization.addTo(new Email(destinatario.getEmail()));

            // Añadir datos dinámicos a la personalización
            for (Map.Entry<String, String> entry : destinatario.getDatos().entrySet()) {
                // La clave del mapa debe coincidir con el marcador en la plantilla (ej: 'nombre' -> {{nombre}})
                personalization.addDynamicTemplateData(entry.getKey(), entry.getValue()); 
            }
            mail.addPersonalization(personalization);
        }

        // Configuración y envío de la solicitud HTTP
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sendGrid.api(request);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                 System.out.println("Envío masivo con SendGrid: Status " + response.getStatusCode() + ". Éxito.");
            } else {
                 // Lanza una excepción en caso de error de la API
                 throw new IOException("Error de SendGrid. Status: " + response.getStatusCode() + ". Cuerpo: " + response.getBody());
            }

        } catch (IOException ex) {
            throw ex;
        }
    }
}