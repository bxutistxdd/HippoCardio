package com.example.hippocardioo.Dto;

public class habitosDTO {

    private Long idHabitos;
    private String nombreHabitos;
    private String descripcionHabitos;
    private String tipoHabitos;

    public habitosDTO(Long idHabitos, String nombreHabitos, String descripcionHabitos, String tipoHabitos) {
        this.idHabitos = idHabitos;
        this.nombreHabitos = nombreHabitos;
        this.descripcionHabitos = descripcionHabitos;
        this.tipoHabitos = tipoHabitos;
    }

    // Getters y Setters
    public Long getIdHabitos() {
        return idHabitos;
    }

    public void setIdHabitos(Long idHabitos) {
        this.idHabitos = idHabitos;
    }

    public String getNombreHabitos() {
        return nombreHabitos;
    }

    public void setNombreHabitos(String nombreHabitos) {
        this.nombreHabitos = nombreHabitos;
    }

    public String getDescripcionHabitos() {
        return descripcionHabitos;
    }

    public void setDescripcionHabitos(String descripcionHabitos) {
        this.descripcionHabitos = descripcionHabitos;
    }

    public String getTipoHabitos() {
        return tipoHabitos;
    }

    public void setTipoHabitos(String tipoHabitos) {
        this.tipoHabitos = tipoHabitos;
    }
}
