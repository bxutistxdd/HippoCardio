package com.example.hippocardioo.Dto;

import java.time.LocalDateTime;

public class metasDTO {

    private Long idMetas;
    private String nombreMetas;
    private String descripcionMetas;
    private String tipoMetas;
    private LocalDateTime fecha;

    public metasDTO(Long idMetas, String nombreMetas, String descripcionMetas, String tipoMetas, LocalDateTime fecha) {
        this.idMetas = idMetas;
        this.nombreMetas = nombreMetas;
        this.descripcionMetas = descripcionMetas;
        this.tipoMetas = tipoMetas;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdMetas() {
        return idMetas;
    }

    public void setIdMetas(Long idMetas) {
        this.idMetas = idMetas;
    }

    public String getNombreMetas() {
        return nombreMetas;
    }

    public void setNombreMetas(String nombreMetas) {
        this.nombreMetas = nombreMetas;
    }

    public String getDescripcionMetas() {
        return descripcionMetas;
    }

    public void setDescripcionMetas(String descripcionMetas) {
        this.descripcionMetas = descripcionMetas;
    }

    public String getTipoMetas() {
        return tipoMetas;
    }

    public void setTipoMetas(String tipoMetas) {
        this.tipoMetas = tipoMetas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
