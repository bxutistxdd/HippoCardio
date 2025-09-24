package com.example.hippocardioo.Dto;

import java.time.LocalDateTime;

public class recordatorioDTO {

    private Long idRecordatorio;
    private String nombreRecordatorio;
    private String tipoRecordatorio;
    private LocalDateTime fecha;

    public recordatorioDTO(Long idRecordatorio, String nombreRecordatorio, String tipoRecordatorio, LocalDateTime fecha) {
        this.idRecordatorio = idRecordatorio;
        this.nombreRecordatorio = nombreRecordatorio;
        this.tipoRecordatorio = tipoRecordatorio;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Long idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombreRecordatorio() {
        return nombreRecordatorio;
    }

    public void setNombreRecordatorio(String nombreRecordatorio) {
        this.nombreRecordatorio = nombreRecordatorio;
    }

    public String getTipoRecordatorio() {
        return tipoRecordatorio;
    }

    public void setTipoRecordatorio(String tipoRecordatorio) {
        this.tipoRecordatorio = tipoRecordatorio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
