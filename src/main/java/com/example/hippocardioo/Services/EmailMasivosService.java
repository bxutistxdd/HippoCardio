package com.example.hippocardioo.Services;

import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.Method;
import java.util.List;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;

import org.springframework.stereotype.Service;

@Service
public class EmailMasivosService {

    private final SendGrid sendGrid;

    public EmailMasivosService() {
        String apiKey = System.getenv("SENDGRID_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("La variable de entorno SENDGRID_API_KEY no está configurada.");
        }
        this.sendGrid = new SendGrid(apiKey);
    }

    // Enviar un solo correo
    public void sendEmail(String to, String subject, String contentText) {
        Email from = new Email("bautistasebastiann@gmail.com");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", contentText);

        Mail mail = new Mail(from, subject, toEmail, content);

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
            System.out.println("Headers: " + response.getHeaders());

        } catch (Exception e) {
            throw new RuntimeException("Error enviando correo: " + e.getMessage(), e);
        }
    }

    // Nuevo método: enviar correos masivos
    public void sendEmails(List<String> destinatarios, String subject, String contentText) {
        for (String email : destinatarios) {
            sendEmail(email, subject, contentText);
        }
    }
}
