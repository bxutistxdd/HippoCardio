package com.example.hippocardioo.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CorreoService {

    private final JavaMailSender mailSender;

    public CorreoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 1. Enviar correos de texto plano (uno por destinatario).
     */
    public void enviarCorreoMasivo(List<String> destinatarios, String asunto, String mensaje) {
        for (String email : destinatarios) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setSubject(asunto);
            mail.setText(mensaje);
            mailSender.send(mail);
        }
    }

    /**
     * 2. Enviar correos masivos usando BCC (más rápido).
     */
    public void enviarCorreoBCC(List<String> destinatarios, String asunto, String mensaje) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setBcc(destinatarios.toArray(new String[0]));
        mail.setSubject(asunto);
        mail.setText(mensaje);
        mailSender.send(mail);
    }

    /**
     * 3. Enviar correos con HTML a varios destinatarios.
     */
    public void enviarCorreoHTML(List<String> destinatarios, String asunto, String htmlContenido) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setTo(destinatarios.toArray(new String[0]));
        helper.setSubject(asunto);
        helper.setText(htmlContenido, true); // true = permite HTML

        mailSender.send(mensaje);
    }

    /**
     * 4. Enviar correos con HTML + adjuntos a varios destinatarios.
     */
    public void enviarCorreoConAdjunto(List<String> destinatarios, String asunto, String htmlContenido, File archivo) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setTo(destinatarios.toArray(new String[0]));
        helper.setSubject(asunto);
        helper.setText(htmlContenido, true); // true = permite HTML
        helper.addAttachment(archivo.getName(), archivo);

        mailSender.send(mensaje);
    }
}
