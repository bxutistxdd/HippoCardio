package com.example.hippocardioo.Dto;

public class gravedadDTO {

    private Long idGravedad;
    private String nombreGravedad;
    private String descripcionGravedad;

    public gravedadDTO(Long idGravedad, String nombreGravedad, String descripcionGravedad) {
        this.idGravedad = idGravedad;
        this.nombreGravedad = nombreGravedad;
        this.descripcionGravedad = descripcionGravedad;
    }

    // Getters y Setters
    public Long getIdGravedad() {
        return idGravedad;
    }

    public void setIdGravedad(Long idGravedad) {
        this.idGravedad = idGravedad;
    }

    public String getNombreGravedad() {
        return nombreGravedad;
    }

    public void setNombreGravedad(String nombreGravedad) {
        this.nombreGravedad = nombreGravedad;
    }

    public String getDescripcionGravedad() {
        return descripcionGravedad;
    }

    public void setDescripcionGravedad(String descripcionGravedad) {
        this.descripcionGravedad = descripcionGravedad;
    }
}
