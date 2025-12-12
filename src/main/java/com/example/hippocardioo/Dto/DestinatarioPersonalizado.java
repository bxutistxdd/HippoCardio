package com.example.hippocardioo.Dto;

import java.util.Map;

public class DestinatarioPersonalizado {

    private String email;
    // Mapa para datos de personalización: {"nombre": "Carlos", "codigo": "ABC"}
    private Map<String, String> datos;

    // Constructor vacío
    public DestinatarioPersonalizado() {}

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, String> datos) {
        this.datos = datos;
    }
}