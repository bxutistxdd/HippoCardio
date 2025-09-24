package com.example.hippocardioo.Dto;

import java.util.List;

public class CorreoRequest {

    private List<String> destinatarios; // lista de destinatarios (soporta uno o varios)
    private String asunto;
    private String contenido; // puede ser texto plano o HTML según el caso

    // Getters y Setters
    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
