package com.example.hippocardioo.Dto;

import java.util.List;

public class CorreoRequest {

    // Lista de destinatarios con sus datos personalizados
    private List<DestinatarioPersonalizado> destinatarios;
    private String asunto;
    private String contenidoHTML; // La plantilla HTML con marcadores (e.g., "Hola {{nombre}}")

    // Constructor vacío
    public CorreoRequest() {}

    // Getters y Setters
    public List<DestinatarioPersonalizado> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<DestinatarioPersonalizado> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenidoHTML() {
        return contenidoHTML;
    }

    public void setContenidoHTML(String contenidoHTML) {
        this.contenidoHTML = contenidoHTML;
    }
}